package com.redbee.io.persistence.repositories;

import com.redbee.io.persistence.entities.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DishRepository extends MongoRepository<Dish, String> {
}
