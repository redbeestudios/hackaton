package io.redbee.services;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import io.redbee.domain.Dish;
import io.redbee.domain.Event;
import io.redbee.domain.Restaurant;
import io.redbee.services.interfaces.TomApiService;

public class TomApiServiceImpl implements TomApiService {
	
	private Client client = ClientBuilder.newClient();
	
	

	@Override
	public Event findActiveEvent() {
		WebTarget target = client.target("http://demo2545284.mockable.io/event");
		return target.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Event>(){});
	}

	@Override
	public List<Restaurant> findRestaurants(String eventId) {
		
		WebTarget target = client.target("http://172.30.0.162:8081/restaurants");

		List<Restaurant> restaurants = target.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Restaurant>>(){});

		
		return restaurants;
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
