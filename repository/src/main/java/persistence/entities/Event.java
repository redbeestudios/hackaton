package persistence.entities;

import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.Date;

public class Event {

    @Id
    private String id;

    private Date date;

    private Collection<Restaurant> restaurants;

    private Collection<User> voters;

    private Restaurant chosenRestaurant;

    private Collection<Vote> votes;

    private Collection<Order> orders;

    private EventState state;

    public Event() {
    }

    public Event(Date date, Collection<Restaurant> restaurants, Collection<User> voters, Restaurant chosenRestaurant,
                 Collection<Vote> votes, Collection<Order> orders, EventState state) {
        this.date = date;
        this.restaurants = restaurants;
        this.voters = voters;
        this.chosenRestaurant = chosenRestaurant;
        this.votes = votes;
        this.orders = orders;
        this.state = state;
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

    public Collection<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Collection<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Collection<User> getVoters() {
        return voters;
    }

    public void setVoters(Collection<User> voters) {
        this.voters = voters;
    }

    public Restaurant getChosenRestaurant() {
        return chosenRestaurant;
    }

    public void setChosenRestaurant(Restaurant chosenRestaurant) {
        this.chosenRestaurant = chosenRestaurant;
    }

    public Collection<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<Vote> votes) {
        this.votes = votes;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }


}
