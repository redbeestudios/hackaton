package com.redbee.io.converter;

import com.redbee.io.persistence.entities.User;
import com.redbee.io.representation.UserRepresentation;

/**
 * Created by martin on 21/10/16.
 */
public class UserConverter {

    public User convert(UserRepresentation userRepresentation){
        User user = new User();
        user.setId(userRepresentation.getId());
        user.setName(userRepresentation.getName());
        user.setPhoneNumber(userRepresentation.getPhoneNumber());
        return user;
    }

    public UserRepresentation convert(User user){
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId(user.getId());
        userRepresentation.setName(user.getName());
        userRepresentation.setPhoneNumber(user.getPhoneNumber());
        return userRepresentation;
    }
}
