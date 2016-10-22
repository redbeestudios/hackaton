package com.redbee.io.service;


import com.redbee.io.converter.EventConverter;
import com.redbee.io.exception.EntityCantBeChange;
import com.redbee.io.persistence.entities.Event;
import com.redbee.io.persistence.entities.EventState;
import com.redbee.io.persistence.repositories.EventRepository;
import com.redbee.io.representation.EventRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventConverter converter;

    @Autowired
    private EventRepository repo;

    public List<Event> getAll() {
        return repo.findAll();
    }

    public Optional<Event> getEvent() {
        return getAll()
                .stream()
                .filter(event -> event.getState().equals(EventState.ORDERING) ||
                        event.getState().equals(EventState.VOTING))
                .findFirst();
    }

    public Event get(String id) {
        return repo.findOne(id);
    }
    public void update(EventRepresentation eventRepresentation, String id) {
        eventRepresentation.setId(id);
        Event event = converter.convert(eventRepresentation);
        repo.save(event);
    }

    public void delete(String id) {
        repo.delete(id);
    }

    public EventRepresentation create(EventRepresentation eventRepresentation) {
        Event event = converter.convert(eventRepresentation);
        return converter.convert(repo.insert(event));
    }

    public Event switchState(EventState state, String id) {
        try {
            Optional.ofNullable(state);
            Event event = repo.findOne(id);
            event.setState(state);
            return repo.save(event);
        } catch (Exception e) {
            throw new EntityCantBeChange();
        }
    }
}
