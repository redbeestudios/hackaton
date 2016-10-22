package com.redbee.io.resources;

import com.redbee.io.persistence.entities.Event;
import com.redbee.io.representation.EventRepresentation;
import com.redbee.io.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/events")
public class EventResource {

    @Autowired
    EventService eventService;



    @RequestMapping(method = RequestMethod.GET)
    public List<Event> list(){
        return eventService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public Event getEvent(@PathVariable String id){
       return eventService.get(id);
    }

    @RequestMapping(method = PUT,value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody EventRepresentation eventRepresentation, @PathVariable("id") String id) {
        eventService.update(eventRepresentation,id);
    }

    @RequestMapping(method = DELETE,value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("id") String id){
        eventService.delete(id);
    }

    @RequestMapping(method = POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public EventRepresentation create(@RequestBody EventRepresentation eventRepresentation){
        return eventService.create(eventRepresentation);
    }

}
