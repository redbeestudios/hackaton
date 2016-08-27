package com.redbee.io.service;


import com.redbee.io.persistence.entities.Event;
import com.redbee.io.persistence.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EventService {

    @Autowired
    private EventRepository repo;

    public List<Event> getAll() {
        return repo.findAll();
    }

}
