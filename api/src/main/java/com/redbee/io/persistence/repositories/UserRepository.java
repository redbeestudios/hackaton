package com.redbee.io.persistence.repositories;

import com.redbee.io.persistence.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

//    User findByPhoneNumber(String phoneNumber);
//
//    User findByName(String name);

}
