package io.redbee.domain;

import java.util.List;

public class Event {

  public enum Status {
      PENDING("PENDING"), VOTING("VOTING"), ORDERING("ORDERING"), CANCELLED("CANCELLED"), CLOSED("CLOSED");

      private String code;
      Status(String code) {
        this.code = code;
      }
  }

	private String eventId;
	private String description;
	private List<Restaurant> restaurants;
	private Status status;


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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

}
