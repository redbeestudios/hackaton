package com.redbee.io.service;

import com.redbee.io.converter.RestaurantConverter;
import com.redbee.io.exception.EntityCantBeDelete;
import com.redbee.io.exception.EntityCantBeSave;
import com.redbee.io.exception.EntityCantBeUpdate;
import com.redbee.io.exception.EntityNotFoundException;
import com.redbee.io.persistence.entities.Restaurant;
import com.redbee.io.persistence.repositories.RestaurantRepository;
import com.redbee.io.representation.RestaurantRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return Optional.ofNullable(repo.findAll()).orElseThrow(EntityNotFoundException::new);
    }

    public RestaurantRepresentation create(RestaurantRepresentation restaurantRespresentation) {
        try {
            Restaurant restaurant = converter.convert(restaurantRespresentation);
            return converter.convert(repo.insert(restaurant));
        }catch(Exception e){
        throw new EntityCantBeSave();
    }

    }

    public void update(RestaurantRepresentation representation,String id) {
        try {
            representation.setId(id);
            Restaurant restaurant = converter.convert(representation);
            repo.save(restaurant);
        }catch (Exception e){
            throw new EntityCantBeUpdate();
        }
    }

    public void delete(String id) {
        try {
            repo.delete(id);
        }catch (Exception e){
            throw new EntityCantBeDelete();
        }
    }

    public Restaurant get(String id) {
        return Optional.ofNullable(repo.findOne(id)).orElseThrow(EntityNotFoundException::new);
    }
}
