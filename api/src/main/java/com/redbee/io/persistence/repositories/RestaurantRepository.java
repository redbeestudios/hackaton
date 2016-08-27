package com.redbee.io.persistence.repositories;

import com.redbee.io.persistence.entities.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

//    Restaurant findByName(String name);




}
