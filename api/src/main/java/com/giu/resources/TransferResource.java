package com.giu.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.giu.representation.UserRepresentation;
import com.giu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.InvalidNameException;
import java.io.UnsupportedEncodingException;


import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by julian on 18/08/16.
 */

@RestController
@RequestMapping("/transfer")
public class TransferResource {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR','ROLE_ADMIN')")
    @RequestMapping(value = "/{employeeID}", method = POST)
    public UserRepresentation transferUser(@PathVariable String employeeID) throws JsonProcessingException,
            UnsupportedEncodingException, InvalidNameException {
        return userService.transferUser(employeeID);
    }


    @PreAuthorize("hasAnyRole('ROLE_OPERATOR','ROLE_ADMIN')")
    @RequestMapping(value = "/{employeeID}", method = DELETE)
    public UserRepresentation deleteUser(@PathVariable String employeeID) throws JsonProcessingException,
            UnsupportedEncodingException, InvalidNameException {
        return userService.deleteUser(employeeID);
    }

}
