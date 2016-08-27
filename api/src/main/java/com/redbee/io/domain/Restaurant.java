package com.redbee.io.domain;

import java.util.List;

/**
 * Created by channo on 26/08/16.
 */
public class Restaurant {

    private String id;
    private String name;
    private List<Dish> dishes;

    public String getId() {
        return id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
