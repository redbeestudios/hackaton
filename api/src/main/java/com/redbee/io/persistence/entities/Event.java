package com.redbee.io.persistence.entities;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Date;

public class Event {

    @Id
    private String id;

    private Date date;

    private List<Restaurant> restaurants;

    private List<User> voters;

    private Restaurant chosenRestaurant;

    private List<Vote> votes;

    private List<Order> orders;

    private EventState state;

    public Event() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<User> getVoters() {
        return voters;
    }

    public void setVoters(List<User> voters) {
        this.voters = voters;
    }

    public Restaurant getChosenRestaurant() {
        return chosenRestaurant;
    }

    public void setChosenRestaurant(Restaurant chosenRestaurant) {
        this.chosenRestaurant = chosenRestaurant;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }


}
