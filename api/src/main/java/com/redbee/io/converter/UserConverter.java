package com.redbee.io.converter;

import com.redbee.io.persistence.entities.User;
import com.redbee.io.representation.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by martin on 21/10/16.
 */

@Component
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

    public List<UserRepresentation> convertListModel(List <User> restaurantList) {
        return restaurantList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public List<User> convertListRepresentation(List <UserRepresentation> restaurantList) {
        return restaurantList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
