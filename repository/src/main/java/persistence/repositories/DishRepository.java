package persistence.repositories;

import persistence.entities.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by manuel.camisas.gays on 8/26/16.
 */
public interface DishRepository extends MongoRepository<Dish, String> {

}
