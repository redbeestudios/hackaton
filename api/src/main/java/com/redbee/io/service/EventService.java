package com.redbee.io.service;


import com.redbee.io.converter.EventConverter;
import com.redbee.io.converter.OrderConverter;
import com.redbee.io.converter.VoteConverter;
import com.redbee.io.exception.EntityCantBeChange;
import com.redbee.io.persistence.entities.Event;
import com.redbee.io.persistence.entities.EventState;
import com.redbee.io.persistence.repositories.EventRepository;
import com.redbee.io.representation.EventRepresentation;
import com.redbee.io.representation.OrderRepresentation;
import com.redbee.io.representation.VoteRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventConverter converter;

    @Autowired
    private VoteConverter voteConverter;

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private EventRepository repo;

    public List<EventRepresentation> getAll() {
        return converter.convertList(repo.findAll());
    }

    public Optional<EventRepresentation> getEvent() {
        return getAll()
                .stream()
                .filter(event -> event.getState().equals(EventState.ORDERING) ||
                        event.getState().equals(EventState.VOTING))
                .findFirst();
    }

    public EventRepresentation get(String id) {
        return converter.convert(repo.findOne(id));
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
        event.setState(EventState.ORDERING);
        return converter.convert(repo.insert(event));
    }

    public VoteRepresentation vote(VoteRepresentation voteRepresentation, String id) {
        Event event = repo.findOne(id);
        if (event.getVotes() == null){
            event.setVotes(new ArrayList<>());
        }
        event.getVotes().add(voteConverter.convertRepresentation(voteRepresentation));
        repo.save(event);
        return voteRepresentation;
    }

    public OrderRepresentation order(OrderRepresentation orderRepresentation, String id) {
        Event event = repo.findOne(id);
        if (event.getOrders() == null){
            event.setOrders(new ArrayList<>());
        }
        event.getOrders().add(orderConverter.convertrepresentation(orderRepresentation));
        repo.save(event);
        return orderRepresentation;

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
