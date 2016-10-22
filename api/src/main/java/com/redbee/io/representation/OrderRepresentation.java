package com.redbee.io.representation;

import com.redbee.io.persistence.entities.Dish;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by fabrizio on 21/10/16.
 */
public class OrderRepresentation {

    @Id
    private String id;
    private String user;
    private List<DishRepresentation> dish;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<DishRepresentation> getDish() {
        return dish;
    }

    public void setDish(List<DishRepresentation> dish) {
        this.dish = dish;
    }
}
