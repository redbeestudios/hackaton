package io.redbee.domain;

import java.util.List;

public class Event {
	private String eventId;
	private String description;
	private List<Restaurant> restaurants;
	private String status;
	
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
