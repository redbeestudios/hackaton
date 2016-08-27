package com.redbee.io.representation;


import java.util.List;

/**
 * Created by martin on 26/08/16.
 */
public class RestaurantRepresentation {

    private String id;
    private String name;
    private List<DishRepresentation> dishes;

    public List<DishRepresentation> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishRepresentation> dishes) {
        this.dishes = dishes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
