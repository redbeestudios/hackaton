package persistence.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import persistence.entities.Dish;

public interface DishRepository extends MongoRepository<Dish, String> {
}
