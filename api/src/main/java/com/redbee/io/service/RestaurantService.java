package com.redbee.io.service;

import com.redbee.io.converter.RestaurantConverter;
import com.redbee.io.persistence.entities.Restaurant;
import com.redbee.io.persistence.repositories.RestaurantRepository;
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
    public RestaurantService(RestaurantConverter converter, RestaurantRepository repo) {
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

    public void update(RestaurantRepresentation representation,String id) {
        representation.setId(id);
        Restaurant restaurant = converter.convert(representation);
        repo.save(restaurant);
    }

    public void delete(String id) {
        repo.delete(id);
    }

    public Restaurant get(String id) {
        return repo.findOne(id);
    }
}
