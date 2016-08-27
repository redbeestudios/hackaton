package com.redbee.io.service;

import com.redbee.io.converter.RestaurantConverter;
import com.redbee.io.domain.Restaurant;
import com.redbee.io.representation.RestaurantRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 26/08/16.
 */
@Service
public class RestaurantService {


    private RestaurantConverter converter;
    private RestaurantRepository repo;

    @Autowired
    RestaurantService(RestaurantConverter converter, RestaurantRepository repository){
        this.converter = converter;
        this.repo = repository;
    }

    public List<Restaurant> getAll() {
        //return repo.findAll();
        List<Restaurant> result = new ArrayList<>();
        Restaurant item = new Restaurant();
        item.setName("Kentucky");
        item.setId("1");
        result.add(item);
        return result;
    }

    public void create(RestaurantRepresentation restaurantRespresentation) {
       Restaurant restaurant = converter.convert(restaurantRespresentation);
        repo.save(restaurant);
    }

    public void update(RestaurantRepresentation representation) {
        Restaurant restaurant = converter.convert(representation);
        repo.update(restaurant);
    }
}
