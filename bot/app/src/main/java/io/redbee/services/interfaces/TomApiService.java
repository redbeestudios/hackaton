package io.redbee.services.interfaces;

import java.util.List;

import io.redbee.domain.Dish;
import io.redbee.domain.Event;
import io.redbee.domain.Restaurant;

public interface TomApiService {
	
	public Event findActiveEvent();
	public List<Restaurant> findRestaurants(String eventId);
	public List<Dish> findDishesForEvent(String eventId);
	public boolean voteRestaurantForEvent(String eventId, String restaurantId, String userId);
	public boolean selectDishForEvent(String eventId, List<String> dishes, String userId);
}
