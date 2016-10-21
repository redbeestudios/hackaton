package com.redbee.io.service;


import com.redbee.io.persistence.entities.Event;
import com.redbee.io.persistence.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventConverter converter;

    @Autowired
    private EventRepository repo;

    public List<Event> getAll() {
        return repo.findAll();
    }

    public EventRepository create(EventRepository eventRespresentation) {
        Event Event = converter.convert(eventRespresentation);
        return converter.convert(repo.insert(Event));
    }

    public void update(EventRepository representation) {
        Event Event = converter.convert(representation);
        repo.save(Event);
    }
    
}
