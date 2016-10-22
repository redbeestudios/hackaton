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
    private UserConverter userConverter;
    private OrderConverter orderConverter;
    private VoteConverter voteConverter;

    @Autowired
    public EventConverter(RestaurantConverter converter1,
                          UserConverter converter2,
                          OrderConverter orderConverter,
                          VoteConverter voteConverter) {
        this.restaurantConverter = converter1;
        this.userConverter = converter2;
        this.orderConverter = orderConverter;
        this.voteConverter = voteConverter;
    }

    public EventRepresentation convert(Event event){
        EventRepresentation result = new EventRepresentation();
        result.setId(event.getId());
        result.setDate(event.getDate());
        result.setState(event.getState());
        result.setRestaurants(restaurantConverter.convertModelList(event.getRestaurants()));
//        result.setVoters(userConverter.convertList(event.getVoters()));
//        result.setChosenRestaurant(restaurantConverter.convert(event.getChosenRestaurant()));
        result.setOrders(orderConverter.convertList(event.getOrders()));
        result.setVotes(voteConverter.convertList(event.getVotes()));
        return result;
    }

    public Event convert(EventRepresentation eventRepresentation) {
        Event result = new Event();
        result.setId(eventRepresentation.getId());
        result.setDate(eventRepresentation.getDate());
        result.setState(eventRepresentation.getState());
        result.setRestaurants(restaurantConverter.convertRepresentationList(eventRepresentation.getRestaurants()));
        //result.setVoters(userConverter.convertList(eventRepresentation.getVoters()));
        //result.setChosenRestaurant(restaurantConverter.convert(eventRepresentation.getChosenRestaurant()));
        result.setOrders(orderConverter.convertListRepresentation(eventRepresentation.getOrders()));
        result.setVotes(voteConverter.convertListRepresentation(eventRepresentation.getVotes()));
        return result;
    }
}