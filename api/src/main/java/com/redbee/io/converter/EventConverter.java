package com.redbee.io.converter;

import com.redbee.io.persistence.entities.Dish;
import com.redbee.io.persistence.entities.Event;
import com.redbee.io.persistence.entities.Restaurant;
import com.redbee.io.persistence.repositories.RestaurantRepository;
import com.redbee.io.representation.DishRepresentation;
import com.redbee.io.representation.EventRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fabrizio on 21/10/16.
 */
@Component
public class EventConverter {


    private RestaurantConverter restaurantConverter;

    @Autowired
    public EventConverter(RestaurantConverter converter) {
        this.restaurantConverter = converter;
    }

    public EventRepresentation convert(Event Event){
        EventRepresentation result = new EventRepresentation();
        result.setId(Event.getId());
        result.setDate(Event.getDate());
        result.setState(Event.getState());


        result.setRestaurants(restaurantConverter.convertList(Event.getRestaurants()));
//        result.setVoters(Event.getVoters());
//        result.setChosenRestaurant(Event.getChosenRestaurant());
//        result.setOrders(Event.getOrders());
//        result.setVotes(Event.getVotes());
//        List<DishRepresentation> dishes = new ArrayList<DishRepresentation>();
//        result.setDishes(dishes);
        return result;
    }

    public Event convert(EventRepresentation EventRepresentation) {
        Event result = new Event();
        result.setId(EventRepresentation.getId());
//        result.setName(EventRepresentation.getName());
//        List<Dish> dishes = new ArrayList<Dish>();
//        EventRepresentation.getDishes().forEach(dish ->
//         dishes.add(new Dish(dish.getName(), dish.getType()))
//        );
//        result.setDishes(dishes);
        return result;

    }
}