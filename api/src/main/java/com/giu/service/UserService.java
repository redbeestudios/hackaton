package com.giu.service;


import com.giu.converter.UserConverter;
import com.giu.domain.OrganizationalUnit;
import com.giu.domain.User;
import com.giu.domain.UserMapper;
import com.giu.exception.BadPasswordException;
import com.giu.exception.BadRequestException;
import com.giu.exception.HashExpiredException;
import com.giu.repositories.OrganizationalUnitRepo;
import com.giu.repositories.UserRepo;
import com.giu.exception.EntityNotFoundException;
import com.giu.repositories.UserTransferRepo;
import com.giu.representation.ReparticionesList;
import com.giu.representation.UserRepresentation;
import com.giu.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.stereotype.Service;

import javax.naming.InvalidNameException;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import java.io.UnsupportedEncodingException;
import java.util.*;


@Service
public class UserService implements BaseLdapNameAware {

    private static final String NORMAL_ACCOUNT = "512";
    private static final String NORMAL_ACCOUNT_ACCOUNTDISABLE = "514";
    private static final String EMPLOYEE_TYPE = "cuil";
    private static final String MAIL_PRINCIPAL_ALTERNATIVE = "@buenosaires.gov.ar";



    private UserRepo userRepo;
    private OrganizationalUnitRepo organizationalUnitRepo;
    private UserTransferRepo userTransferRepo;
    private UserConverter converter;
    private MailService mailService;
    private LdapName baseLdapPath;
    private LdapTemplate ldapTemplate;
    private RedisService redisService;
    private PasswordGeneratorService passGeneratorSrv;
    private BaseUtils baseUtils;

    @Autowired
    public UserService(UserRepo userRepo, UserConverter converter,
                       MailService mailService, RedisService redisService,
                       PasswordGeneratorService passwordGeneratorService,
                       LdapTemplate ldapTemplate,
                       BaseUtils baseUtils,
                       UserTransferRepo userTransferRepo,
                       OrganizationalUnitRepo organizationalUnitRepo
                       ) {
        this.userRepo = userRepo;
        this.converter = converter;
        this.mailService = mailService;
        this.redisService = redisService;
        this.passGeneratorSrv = passwordGeneratorService;
        this.ldapTemplate = ldapTemplate;
        this.baseUtils = baseUtils;
        this.userTransferRepo = userTransferRepo;
        this.organizationalUnitRepo = organizationalUnitRepo;
    }

    public void sendForgetPassword(UserRepresentation userRepresentation){
        User user = userRepo.findByEmployeeID(userRepresentation.getEmployeeID()).orElseThrow(EntityNotFoundException::new);
        String pwd = passGeneratorSrv.generatorPassword();
        user.setUnicodePwd(passGeneratorSrv.encodePassword(pwd));
        userRepo.save( user);
        mailService.sendMeNewPassword(user,pwd);
    }


    public UserRepresentation findByEmployeeId(String employeeID) {
        User user = userRepo.findByEmployeeID(employeeID)
                .orElseThrow(EntityNotFoundException::new);
        UserRepresentation result = converter.convert(user);
        result.setDgtal(baseUtils.getOU(user.getDn()));
        return result;
    }


    public UserRepresentation findByDistinguishedName(String distinguishedName) {
        User user = userRepo.findByDistinguishedName(distinguishedName)
                .orElseThrow(EntityNotFoundException::new);
        UserRepresentation result = converter.convert(user);
        result.setDgtal(baseUtils.getOU(user.getDn()));
        return result;
    }


    public List<UserRepresentation> findAllUsers(String query){
        List<UserRepresentation> userList = converter.convert(userRepo.findAllUsers(query));
        userList.forEach( it -> {
            it.setDgtal(baseUtils.getOU(it.getDn()));
        });

        List<UserRepresentation> userListTransferido = converter.convert(userTransferRepo.findAllUsersByNameOrEmail(query));
        userListTransferido.forEach( it ->{
            it.setDgtal(baseUtils.getOU(it.getDn()));
            it.setIsTransfer(true);
        });
        userList.addAll(userListTransferido);
        return userList;
    }

    public void createUser(UserRepresentation userRepresentation) throws UnsupportedEncodingException, InvalidNameException {
        User user = converter.convert(userRepresentation);
        userRepo.findByEmployeeID(user.getEmployeeID()).ifPresent( existUser -> {
            throw new  EntityNotFoundException();
        });
       // String pwd = passGeneratorSrv.generatorPassword();
       // user.setUnicodePwd(passGeneratorSrv.encodePassword(pwd));
        user.setUserAccountControl(NORMAL_ACCOUNT_ACCOUNTDISABLE);
        user.setEmployeeType(EMPLOYEE_TYPE);
        String[] alias = user.getMail().split("@");
        String mailPrincipalAlternative = alias[0].toString().concat(MAIL_PRINCIPAL_ALTERNATIVE).toString();
        if(!Optional.ofNullable(user.getOtherMailbox()).isPresent()){
            List<String> list = new ArrayList<>();
            user.setOtherMailbox(list);
        }
        user.getOtherMailbox().add(mailPrincipalAlternative);

        //TODO:Si el optional es null hay un problema guardando el usuario
        User savedUser = userRepo.save(user).get();
        Optional.ofNullable(savedUser).ifPresent( newUser ->{
            mailService.register(savedUser);
        });
    }

    public UserRepresentation updateUser(UserRepresentation userRepresentation) throws UnsupportedEncodingException{

        User user = userRepo.findByEmployeeID(userRepresentation.getEmployeeID())
                .orElseThrow(EntityNotFoundException::new);
        //valido los parametro obligatorios sean correctos
        //Optional.ofNullable(userRepresentation.getOtherMailbox()).orElseThrow(EntityNotFoundException::new);
        Optional.ofNullable(userRepresentation.getNewPassword()).ifPresent(pass ->{
            if(!userRepresentation.getNewPassword().equals(userRepresentation.getNewPasswordCheck())) {
                throw new BadRequestException();
            }
            if(!userRepo.attempAuthenticate(userRepresentation.getEmployeeID(), userRepresentation.getOldPassword())){
                throw new BadPasswordException();
            }
        });
        //TODO:Si el optional es null hay un problema guardando el usuario
        String pwd = userRepresentation.getNewPasswordCheck();
        user.setUnicodePwd(passGeneratorSrv.encodePassword(pwd));
        Optional<User> result = userRepo.save(converter.convert(userRepresentation, user));
        return converter.convert(result.get());
    }

    public UserRepresentation validAccount(String hash,UserRepresentation userRepresentation) throws InvalidNameException, HashExpiredException {
        String userEmployeeId = (String) redisService.getValue(hash);
        Optional.ofNullable(userEmployeeId).orElseThrow(HashExpiredException::new);
        //habilitamos la cuenta
        User user = userRepo.findByEmployeeID(userEmployeeId).orElseThrow(EntityNotFoundException::new);

        //TODO:Si el optional es null hay un problema guardando el usuario
        user.setUserAccountControl(NORMAL_ACCOUNT);
        String pwd = userRepresentation.getNewPasswordCheck();
        user.setUnicodePwd(passGeneratorSrv.encodePassword(pwd));
        Optional<User> result = userRepo.save(user);
        redisService.deleteValue(hash); //TODO: ver q pasa si se ingresa al link o si expira el hash
        return converter.convert(result.get());

    }

    public UserRepresentation transferUser(String employeeID){
        try{
            User user = userRepo.findByEmployeeID(employeeID).orElseThrow(EntityNotFoundException::new);
            return converter.convert(userTransferRepo.transferUser(user));
        }catch(Exception e){
            throw new EntityNotFoundException();
        }
    }

    public UserRepresentation deleteUser(String employeeID){
        try{
            User user = userTransferRepo.findByEmployeeID(employeeID).orElseThrow(EntityNotFoundException::new);
            userTransferRepo.deleteUser(user);
            return converter.convert(userRepo.findByEmployeeID(employeeID).orElseThrow(EntityNotFoundException::new));
        }catch(Exception e){
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void setBaseLdapPath(LdapName baseLdapPath) {
        this.baseLdapPath = baseLdapPath;
    }


    public UserRepresentation removeUser(String employeeID) {
        try{
            User user = userRepo.findByEmployeeID(employeeID).orElseThrow(EntityNotFoundException::new);
            user.setUserAccountControl(NORMAL_ACCOUNT_ACCOUNTDISABLE); //deshabilita la cuenta
            Optional <User> result =   userRepo.update(user); // actualiza el user y lo transfiere
            return converter.convert(userTransferRepo.transferUser(result.get()));

        }catch(Exception e){
            throw new EntityNotFoundException();
        }
    }

    public ReparticionesList getReparticiones(String distinguishedName){
        String dnOu = baseUtils.getDnOfMyOU(distinguishedName);
        OrganizationalUnit myOu = organizationalUnitRepo.findByDistinguishedName(dnOu).orElseThrow(EntityNotFoundException::new);
        List <OrganizationalUnit> listOu = organizationalUnitRepo.findAllOuByAdminDescription(myOu.getDn());
        ReparticionesList result = new ReparticionesList();
        listOu.forEach( ou -> result.getReparticiones().add(ou.getOu()));
        return result;
    }
}
