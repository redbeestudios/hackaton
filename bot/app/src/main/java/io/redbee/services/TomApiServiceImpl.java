package io.redbee.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import io.redbee.domain.Dish;
import io.redbee.domain.Event;
import io.redbee.domain.EventVoting;
import io.redbee.domain.Restaurant;
import io.redbee.services.interfaces.TomApiService;

public class TomApiServiceImpl implements TomApiService {
	
	private Client client = ClientBuilder.newClient();
	
	

	@Override
	public Event findActiveEvent() {
		// TODO Auto-generated method stub
		return new EventVoting();
	}

	@Override
	public List<Restaurant> findRestaurants(String eventId) {
		/*
		WebTarget target = client.target("http://172.30.0.162:8081/restaurants");

		List<Restaurant> restaurants = target.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Restaurant>>(){});
*/
    List<Restaurant> restaurants = new ArrayList<>();
    Restaurant ke = new Restaurant();
    ke.setDescription("Kentucky");

    restaurants.add(ke);
    Restaurant pa = new Restaurant();
    pa.setDescription("Palacio");
    restaurants.add(pa);

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
