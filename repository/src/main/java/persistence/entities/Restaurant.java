package persistence.entities;


import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.List;

public class Restaurant {
    @Id
    private String id;


    private String name;

    private Collection<Dish> dishes;


    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name=name;
    }


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


    public Collection<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Collection<Dish> dishes) {
        this.dishes = dishes;
    }
}
