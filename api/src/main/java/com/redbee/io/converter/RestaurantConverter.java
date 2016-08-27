package com.redbee.io.converter;

import com.redbee.io.representation.DishRepresentation;
import com.redbee.io.representation.RestaurantRepresentation;
import org.springframework.stereotype.Component;
import persistence.entities.Dish;
import persistence.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 26/08/16.
 */
@Component
public class RestaurantConverter {

    public RestaurantRepresentation convert(Restaurant restaurant){
        RestaurantRepresentation result = new RestaurantRepresentation();
        result.setId(restaurant.getId());
        result.setName(restaurant.getName());
        List<DishRepresentation> dishes = new ArrayList<DishRepresentation>();
//        restaurant.getDishes().forEach(dish ->
//                dishes.add(new DishRepresentation(dish.getType(), dish.getFlavour()))
//        );
        result.setDishes(dishes);
        return result;
    }

    public Restaurant convert(RestaurantRepresentation restaurantRepresentation) {
        Restaurant result = new Restaurant(restaurantRepresentation.getName());
        result.setId(restaurantRepresentation.getId());
        List<Dish> dishes = new ArrayList<Dish>();
//        restaurantRepresentation.getDishes().forEach(dish ->
//         dishes.add(new Dish(dish.getType(), dish.getFlavour()))
//        );
        result.setDishes(dishes);
        return result;

    }
}
