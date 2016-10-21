package io.redbee.services;

import java.util.List;

import io.redbee.domain.Dish;
import io.redbee.domain.Restaurant;
import io.redbee.services.interfaces.TomApiService;

public class TomApiServiceImpl implements TomApiService {

	@Override
	public String getEventId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> findRestaurants(String eventId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dish> findDishesForEvent(String eventId, String restaurantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean voteRestaurantForEvent(String eventId, String restaurantId) {
		// TODO Auto-generated method stub
		return false;
	}

}
