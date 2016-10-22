package com.redbee.io.persistence.entities;

import org.springframework.data.annotation.Id;


public class Dish {
    @Id
    private String id;

    private String name;

    private String type;


    public Dish(){}
    public Dish(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
