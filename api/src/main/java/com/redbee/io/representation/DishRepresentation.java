package com.redbee.io.representation;

/**
 * Created by martin on 26/08/16.
 */
public class DishRepresentation {
    private String type;
    private String flavour;

    public DishRepresentation(String type, String flavour) {
        this.type = type;
        this.flavour = flavour;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }
}
