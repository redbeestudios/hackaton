package com.redbee.io.service;


import com.redbee.io.converter.EventConverter;
import com.redbee.io.persistence.entities.Event;
import com.redbee.io.persistence.repositories.EventRepository;
import com.redbee.io.representation.EventRepresentation;
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

    public Event get(String id) {
        return repo.findOne(id);
    }

    public EventRepresentation create(EventRepresentation eventRespresentation) {
        Event Event = converter.convert(eventRespresentation);
        return converter.convert(repo.insert(Event));
    }

    public void update(EventRepresentation representation) {
        Event Event = converter.convert(representation);
        repo.save(Event);
    }
    
}
