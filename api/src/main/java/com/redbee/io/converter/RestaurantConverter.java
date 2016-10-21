package com.redbee.io.converter;

import com.redbee.io.persistence.entities.Dish;
import com.redbee.io.persistence.entities.Restaurant;
import com.redbee.io.representation.DishRepresentation;
import com.redbee.io.representation.RestaurantRepresentation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        restaurant.getDishes().forEach(dish ->
                dishes.add(new DishRepresentation(dish.getName(), dish.getType()))
        );
        result.setDishes(dishes);
        return result;
    }

    public Restaurant convert(RestaurantRepresentation restaurantRepresentation) {
        //TODO: Tomar el ID de la Base
        Restaurant result = new Restaurant();
        if(Optional.ofNullable(restaurantRepresentation.getId()).isPresent()){
            result.setId(restaurantRepresentation.getId());
        }
        result.setName(restaurantRepresentation.getName());
        List<Dish> dishes = new ArrayList<Dish>();
        restaurantRepresentation.getDishes().forEach(dish ->
         dishes.add(new Dish(dish.getName(), dish.getType()))
        );
        result.setDishes(dishes);
        return result;

    }


    public List<RestaurantRepresentation> convertList1(List <Restaurant> restaurantList) {
        List<RestaurantRepresentation> result = restaurantList.stream().map(this::converToRepresentation).collect(Collectors.toList());
        return result;
    }

    public List<Restaurant> convertList(List <RestaurantRepresentation> restaurantRepresentationList) {
        return restaurantRepresentationList.stream().map(this::converToModel).collect(Collectors.toList());
    }


    private Restaurant converToModel(RestaurantRepresentation representation) {
        return convert(representation);
    }

    private RestaurantRepresentation converToRepresentation(Restaurant model) {
        return convert(model);
    }
}
