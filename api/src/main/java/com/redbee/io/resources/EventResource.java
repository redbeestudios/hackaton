package com.redbee.io.resources;

import com.redbee.io.persistence.entities.Event;
import com.redbee.io.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventResource {

    @Autowired
    EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Event> list(){
        return eventService.getAll();
    }

}
