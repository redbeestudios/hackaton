package com.giu.converter;

import com.giu.domain.GcbaUserDetail;
import com.giu.domain.User;
import com.giu.representation.UserRepresentation;
import com.giu.service.PasswordGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by martin on 22/07/16.
 */
@Component
public class UserConverter {

    private PasswordGeneratorService passGeneratorSrv;
    private Map<String, String> state= new HashMap<>();
    private Map<String, String> employeeType= new HashMap<>();

    @Autowired
    public UserConverter(PasswordGeneratorService passGeneratorSrv){
        this.passGeneratorSrv=passGeneratorSrv;
        this.state.put("514","Deshabilitado");
        this.state.put("512","Habilitado");
        this.employeeType.put("cuil","Usuario");
        this.employeeType.put("cuit","Usuario");
        this.employeeType.put("GenÃ©rica","Mail");
    }

    public UserRepresentation convert(User user){
        UserRepresentation result = new UserRepresentation();
        result.setEmployeeID(user.getEmployeeID());
        result.setState(state.get(user.getUserAccountControl()));
        result.setEmployeeType(employeeType.get(user.getEmployeeType()));
        result.setCn(user.getCn());
        result.setMail(user.getMail());
        result.setEmployeeNumber(user.getEmployeeNumber());
        result.setTelephoneNumber(user.getPhone());
        result.setLastName(user.getLastName());
        result.setName(user.getGivenName());
        result.setWorkRelationship(user.getWorkRelationship());
        result.setOtherMailbox(user.getOtherMailbox());
        result.setStreetAddress(user.getStreetAddress());
        result.setDn(String.valueOf(user.getId()));
        result.setOldPassword(user.getOldPassword());
        result.setMembersOf(user.getMembersOf());
        result.setAlternativeMail(user.getAlternativeMail());
        return result;
    }

    public List<UserRepresentation> convert(List<User> users){
        List<UserRepresentation> result = new ArrayList<UserRepresentation>();
      users.forEach((user -> {
          UserRepresentation userRepresent = new UserRepresentation();
          userRepresent.setEmployeeID(user.getEmployeeID());
          userRepresent.setDn(user.getDn());
          userRepresent.setCn(user.getCn());
          userRepresent.setMail(user.getMail());
          userRepresent.setEmployeeNumber(user.getEmployeeNumber());
          userRepresent.setLastName(user.getLastName());
          userRepresent.setName(user.getGivenName());
          userRepresent.setWorkRelationship(user.getWorkRelationship());
          userRepresent.setOtherMailbox(user.getOtherMailbox());
          userRepresent.setAlternativeMail(user.getAlternativeMail());
          userRepresent.setEmployeeType(employeeType.get(user.getEmployeeType()));
          userRepresent.setState(state.get(user.getUserAccountControl()));
          userRepresent.setStreetAddress(user.getStreetAddress());
           result.add(userRepresent);
      }));

        return result;
    }

    public User convert(UserRepresentation userRepresentation) {
        User result = new User();
        result.setEmployeeID(userRepresentation.getEmployeeID());
        result.setsAMAccountName(userRepresentation.getEmployeeID());
        result.setCn(userRepresentation.getName() + ' ' + userRepresentation.getLastName());
        result.setMail(userRepresentation.getMail());
        result.setPhone(userRepresentation.getTelephoneNumber());
        result.setLastName(userRepresentation.getLastName());
        result.setName(userRepresentation.getName());
        result.setWorkRelationship(userRepresentation.getWorkRelationship());
        result.setOtherMailbox(userRepresentation.getOtherMailbox());
        result.setAlternativeMail(userRepresentation.getAlternativeMail());
        result.setStreetAddress(userRepresentation.getStreetAddress());
        result.setOldPassword(userRepresentation.getOldPassword());
        Optional.ofNullable(userRepresentation.getNewPassword()).ifPresent( pwd -> {
            result.setUnicodePwd(passGeneratorSrv.encodePassword(pwd));
        });
        return result;
    }

    public User convert(UserRepresentation userRepresentation, User user) {
        user.setMail(userRepresentation.getMail());
        user.setAlternativeMail(userRepresentation.getAlternativeMail());
        user.setPhone(userRepresentation.getTelephoneNumber());
        user.setWorkRelationship(userRepresentation.getWorkRelationship());
        user.setStreetAddress(userRepresentation.getStreetAddress());
        user.setOldPassword(userRepresentation.getOldPassword());
        Optional.ofNullable(userRepresentation.getNewPassword()).ifPresent( pwd ->
            user.setUnicodePwd(passGeneratorSrv.encodePassword(pwd))
        );
        return user;
    }

    public GcbaUserDetail convertToUserDetail(User user){
        GcbaUserDetail gcbaUserDetail = new GcbaUserDetail();
        gcbaUserDetail.setDistinguishedName(user.getDn());
        return gcbaUserDetail;
    }


}
