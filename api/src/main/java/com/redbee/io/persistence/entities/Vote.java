package com.redbee.io.persistence.entities;


import org.springframework.data.annotation.Id;


public class Vote {
    @Id
    private String id;

    private String user;
    private String restaurant;

    public Vote() {
    }
    public Vote(String user, String restaurant) {
        this.user = user;
        this.restaurant = restaurant;
    }

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
