package com.giu.resources;

import com.giu.domain.GcbaUserDetail;
import com.giu.representation.ReparticionesList;
import com.giu.representation.UserRepresentation;
import com.giu.service.UserService;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by julian on 17/08/16.
 */

@RestController
@RequestMapping("/operator")
public class OperatorResource {

    @Autowired
    private UserService userService;

    //@PreAuthorize("hasAnyRole('ROLE_OPERATOR','ROLE_ADMIN')")
    @RequestMapping(method = GET)
    public UserRepresentation getUser(Principal principal) throws JsonProcessingException {
        GcbaUserDetail gcbaUserDetail = (GcbaUserDetail)((OAuth2Authentication) principal).getUserAuthentication().getPrincipal();
        return userService.findByDistinguishedName(gcbaUserDetail.getDistinguishedName());
    }

    @RequestMapping(value = "/{employeeID}", method = PUT)
    public UserRepresentation updateUser(@RequestBody UserRepresentation user) throws com.fasterxml.jackson.core.JsonProcessingException, UnsupportedEncodingException {
        return userService.updateUser(user);

    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR','ROLE_ADMIN')")
    @RequestMapping(value = "/reparticiones", method = GET)
    public ReparticionesList getReparticiones(Principal principal) throws JsonProcessingException {
        GcbaUserDetail gcbaUserDetail = (GcbaUserDetail)((OAuth2Authentication) principal).getUserAuthentication().getPrincipal();
        return userService.getReparticiones(gcbaUserDetail.getDistinguishedName());
    }

}
