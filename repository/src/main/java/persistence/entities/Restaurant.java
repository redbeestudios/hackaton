package persistence.entities;


import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Collection;

public class Restaurant {
    @Id
    private String id;

    private String name;

    private Collection<Dish> dishes=new ArrayList<Dish>();

    public Restaurant(){}


    public Restaurant(String name, Collection<Dish> dishes) {
        this.name = name;
        this.dishes = dishes;
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

    public void addDish(Dish dish){
        dishes.add(dish);
    }
}
