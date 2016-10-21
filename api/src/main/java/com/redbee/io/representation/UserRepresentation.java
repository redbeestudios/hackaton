package com.redbee.io.representation;

import org.springframework.data.annotation.Id;

/**
 * Created by martin on 21/10/16.
 */
public class UserRepresentation {

    @Id
    private String id;
    private String phoneNumber;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


