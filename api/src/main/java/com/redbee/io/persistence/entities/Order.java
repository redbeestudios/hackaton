package com.redbee.io.persistence.entities;


import org.springframework.data.annotation.Id;

public class Order {
    @Id
    private String id;
    private User user;
    private Dish dish;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
