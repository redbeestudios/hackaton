package persistence.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import persistence.entities.User;


public interface UserRepository extends MongoRepository<User, String> {

    User findByPhoneNumber(String phoneNumber);

    User findByName(String name);

}
