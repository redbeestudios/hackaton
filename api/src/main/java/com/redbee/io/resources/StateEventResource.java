package com.redbee.io.resources;

import com.redbee.io.persistence.entities.Event;
import com.redbee.io.persistence.entities.EventState;
import com.redbee.io.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by martin on 22/10/16.
 */
@RestController
@RequestMapping("/state")
public class StateEventResource {

    @Autowired
    EventService eventService;


    @RequestMapping(method = PUT,value = "/{eventID}/state/{state}")
    @ResponseStatus(value = HttpStatus.OK)
    public Event changeState(@PathVariable String eventID, @PathVariable EventState state){
        return eventService.switchState(state,eventID);
    }
}
