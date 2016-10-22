package io.redbee.services;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.redbee.domain.Dish;
import io.redbee.domain.Event;
import io.redbee.domain.Order;
import io.redbee.domain.Restaurant;
import io.redbee.domain.VotingResto;
import io.redbee.services.interfaces.TomApiService;
import io.redbee.utils.EventFactory;

public class TomApiServiceImpl implements TomApiService {
	
	private Client client = ClientBuilder.newClient();
	
	private String intIP = "http://172.16.21.38:8080";

	@Override
	public Event findActiveEvent() {
		WebTarget target = client.target(intIP + "/currentEvent");
//		WebTarget target = client.target("http://demo2545284.mockable.io/event");
		Event event = null ;
		try {
			event = EventFactory.getEvent( target.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Event>(){}));
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event;
	}

	@Override
	public List<Restaurant> findRestaurants(String eventId) {

		WebTarget target = client.target(intIP + "/restaurants");

		List<Restaurant> restaurants = target.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Restaurant>>(){});

//    List<Restaurant> restaurants = new ArrayList<>();
//    Restaurant ke = new Restaurant();
//    ke.setName("Kentucky");
//
//    restaurants.add(ke);
//    Restaurant pa = new Restaurant();
//    pa.setName("Palacio");
//    restaurants.add(pa);

		return restaurants;
	}

	@Override
	public List<Dish> findDishesForEvent(String eventId) {
		List<Dish> dishes = new ArrayList<>();
    Dish empa = new Dish();
    empa.setName("Empanada de jamon");
    empa.setDishId("12");
    dishes.add(empa);

    Dish empa1 = new Dish();
    empa1.setName("Pizza de Choclo");
    empa1.setDishId("12");
    dishes.add(empa1);


		return dishes;
	}

  @Override
  public boolean voteRestaurantForEvent(String eventId, String restaurantId, String userId) {
	  
	  WebTarget target = client.target(intIP + "/events");
	  target.path(eventId).path("resto");
	  
	  VotingResto resto = new VotingResto(restaurantId,userId);
	  
	
		
		Entity<VotingResto> entity =  Entity.entity(resto, MediaType.APPLICATION_JSON_TYPE);
		
		Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).post(entity);
		
		
		return (Boolean) resp.getEntity();

  }

  @Override
  public boolean selectDishForEvent(String eventId, List<String> dishes, String userId) {
	
		WebTarget target = client.target(intIP + "/events");
		target.path(eventId).path("order");

		Order order = new Order(dishes, userId);

		Entity<Order> entity = Entity.entity(order, MediaType.APPLICATION_JSON_TYPE);

		Response resp = target.request(MediaType.APPLICATION_JSON_TYPE).post(entity);

		return (Boolean) resp.getEntity();
  
  }

}
