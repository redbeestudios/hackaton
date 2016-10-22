package com.redbee.io.persistence.repositories;

import com.redbee.io.persistence.entities.Event;
import com.redbee.io.persistence.entities.Order;
import com.redbee.io.persistence.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Collection;

public interface OrderRepository extends MongoRepository<Order, String> {

}
