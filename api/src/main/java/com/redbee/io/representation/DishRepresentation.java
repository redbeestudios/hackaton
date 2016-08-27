package com.redbee.io.representation;

/**
 * Created by martin on 26/08/16.
 */
public class DishRepresentation {
    private String name;
    private String type;

    public DishRepresentation(String name, String type) {
        this.name = name;
        this.type = type;
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
