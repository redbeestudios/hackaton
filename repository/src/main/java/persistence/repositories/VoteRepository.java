package persistence.repositories;


import persistence.entities.*;

import java.util.Collection;

public interface VoteRepository {


    Collection<Vote> findByUser(User user);
    Collection<Vote> findByRestaurant(Restaurant restaurant);

}
