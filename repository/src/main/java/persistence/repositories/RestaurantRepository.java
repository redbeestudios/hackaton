package persistence.repositories;

import persistence.entities.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    Restaurant findByName(String name);




}
