package com.redbee.io.persistence.repositories;

import com.redbee.io.persistence.entities.Event;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EventRepository extends MongoRepository<Event, String> {

}
