package persistence.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import persistence.entities.*;

import java.util.Collection;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    Collection<Order> findByUser(User user);
    Collection<Order> findByEvent(Event event);
}
