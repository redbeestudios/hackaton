package com.redbee.io.resources;

import com.redbee.io.persistence.entities.Event;
import com.redbee.io.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by martin on 21/10/16.
 */
@RestController
@RequestMapping("/currentEvent")
public class CurrentEventResource {

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public Optional<Event> eventOn(){
        return eventService.getEvent();
    }
}
