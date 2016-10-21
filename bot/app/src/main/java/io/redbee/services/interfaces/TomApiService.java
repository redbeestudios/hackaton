package io.redbee.services.interfaces;

import java.util.List;


import io.redbee.domain.Dish;
import io.redbee.domain.Restaurant;

public interface TomApiService {
	
	public String getEventId();
	public List<Restaurant> findRestaurants(String eventId);
	public List<Dish> findDishesForEvent(String eventId, String restaurantId);
	public boolean voteRestaurantForEvent(String eventId, String restaurantId);
	

}
