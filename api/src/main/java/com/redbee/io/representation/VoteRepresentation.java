package com.redbee.io.representation;

import com.redbee.io.persistence.entities.User;

/**
 * Created by fabrizio on 21/10/16.
 */
public class VoteRepresentation {

    private String id;

    private String user;
    private String restaurant;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}
