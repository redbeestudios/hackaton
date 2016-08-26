package com.giu.resources;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.giu.exception.BadPasswordException;
import com.giu.exception.EntityNotFoundException;
import com.giu.exception.HashExpiredException;
import com.giu.representation.UserRepresentation;
import com.giu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;


import javax.naming.InvalidNameException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR','ROLE_ADMIN')")
    @RequestMapping(method = GET)
    public @ResponseBody Page<UserRepresentation> findAllUsers(@RequestParam("nameOrMail") String nameOrMail, OAuth2Authentication user) throws JsonProcessingException {
        return new PageImpl<UserRepresentation>(userService.findAllUsers(nameOrMail));
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR','ROLE_ADMIN')")
    @RequestMapping(value = "/{employeeID}", method = GET)
    public UserRepresentation getUser(@PathVariable String employeeID) throws JsonProcessingException {
        return userService.findByEmployeeId(employeeID);
    }

    @RequestMapping(value = "/password/{hash}", method = POST)
    public UserRepresentation enabledAccount(@PathVariable String hash, @RequestBody UserRepresentation userRepresentation) throws JsonProcessingException, InvalidNameException, HashExpiredException {
        return userService.validAccount(hash, userRepresentation);
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR','ROLE_ADMIN')")
    @RequestMapping(method = POST)
    public void createUser(@RequestBody UserRepresentation user) throws JsonProcessingException,
            UnsupportedEncodingException, InvalidNameException {
        userService.createUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR','ROLE_ADMIN')")
    @RequestMapping(value = "/{employeeID}", method =DELETE)
    public UserRepresentation removeUser(@PathVariable String employeeID) throws JsonProcessingException,
            UnsupportedEncodingException, InvalidNameException {
        return userService.removeUser(employeeID);
    }

    @RequestMapping(value = "/{employeeID}", method = PUT)
    public UserRepresentation updateUser(@RequestBody UserRepresentation user) throws JsonProcessingException, UnsupportedEncodingException {
        return userService.updateUser(user);

    }

    @RequestMapping(value = "/forget", method = POST)
    public void sendForgetPassword(@RequestBody UserRepresentation user) throws JsonProcessingException {
        userService.sendForgetPassword(user);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public Map response() {
        Map result = new HashMap<>();
        result.put("message", "Entity not found");
        return result;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadPasswordException.class)
    public Map response(BadPasswordException ex) {
        Map result = new HashMap<>();
        result.put("message", "The old password is wrong");
        return result;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(HashExpiredException.class)
    public Map error(HashExpiredException ex) {
        Map result = new HashMap<>();
        result.put("message", "The Hash was expired");
        return result;
    }


}