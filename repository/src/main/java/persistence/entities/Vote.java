package persistence.entities;


import org.springframework.data.annotation.Id;

import javax.jws.soap.SOAPBinding;

public class Vote {
    @Id
    private String id;

    private User user;
    private Restaurant restaurant;


    public Vote(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
