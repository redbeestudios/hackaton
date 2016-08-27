package com.redbee.io.service;

import com.redbee.io.converter.RestaurantConverter;
import com.redbee.io.representation.RestaurantRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.entities.Restaurant;
import persistence.repositories.RestaurantRepository;

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
    RestaurantService(RestaurantConverter converter, RestaurantRepository repo){
        this.converter = converter;
        this.repo = repo;
    }

    public List<Restaurant> getAll() {
        return repo.findAll();
    }

    public RestaurantRepresentation create(RestaurantRepresentation restaurantRespresentation) {
       Restaurant restaurant = converter.convert(restaurantRespresentation);
       return converter.convert(repo.insert(restaurant));
    }

    public void update(RestaurantRepresentation representation) {
        Restaurant restaurant = converter.convert(representation);
        repo.save(restaurant);
    }
}
