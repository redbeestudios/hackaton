package persistence.repositories;

import persistence.entities.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by manuel.camisas.gays on 8/26/16.
 */
public interface EventRepository extends MongoRepository<Event, String> {

}
