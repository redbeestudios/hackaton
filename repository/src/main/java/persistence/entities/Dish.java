package persistence.entities;

import org.springframework.data.annotation.Id;

/**
 * Created by manuel.camisas.gays on 8/26/16.
 */

public class Dish {
    @Id
    private String id;

    private String name;

    public Dish(String name) {
        this.name = name;
    }
}
