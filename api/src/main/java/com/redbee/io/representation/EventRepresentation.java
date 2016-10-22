package com.redbee.io.representation;

import com.redbee.io.persistence.entities.*;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * Created by martin on 21/10/16.
 */
public class EventRepresentation {

    @Id
    private String id;

    private Date date;

    private List<RestaurantRepresentation> restaurants;

    private String chosenRestaurant;

    private List<VoteRepresentation> votes;

    private List<OrderRepresentation> orders;

    private EventState state;

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

    public List<RestaurantRepresentation> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantRepresentation> restaurants) {
        this.restaurants = restaurants;
    }


    public String getChosenRestaurant() {
        return chosenRestaurant;
    }

    public void setChosenRestaurant(String chosenRestaurant) {
        this.chosenRestaurant = chosenRestaurant;
    }

    public List<VoteRepresentation> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteRepresentation> votes) {
        this.votes = votes;
    }

    public List<OrderRepresentation> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderRepresentation> orders) {
        this.orders = orders;
    }

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }


}
