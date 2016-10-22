package com.redbee.io.representation;

import org.springframework.data.annotation.Id;

/**
 * Created by fabrizio on 21/10/16.
 */
public class OrderRepresentation {

    @Id
    private String id;
    private UserRepresentation user;
    private DishRepresentation dish;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserRepresentation getUser() {
        return user;
    }

    public void setUser(UserRepresentation user) {
        this.user = user;
    }

    public DishRepresentation getDish() {
        return dish;
    }

    public void setDish(DishRepresentation dish) {
        this.dish = dish;
    }
}
