package com.redbee.io.persistence.entities;


import org.springframework.data.annotation.Id;

import java.util.List;

public class Order {
    @Id
    private String id;
    private String user;
    private List<Dish> dish;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Dish> getDish() {
        return dish;
    }

    public void setDish(List<Dish> dish) {
        this.dish = dish;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
