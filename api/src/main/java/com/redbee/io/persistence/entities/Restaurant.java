package com.redbee.io.persistence.entities;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;


public class Restaurant {
    @Id
    private String id;

    private String name;

    private List<Dish> dishes=new ArrayList<>();

    public String getId() {
        return id;
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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDish(Dish dish){
        dishes.add(dish);
    }
}
