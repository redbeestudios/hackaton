package com.redbee.io.resources;

import com.redbee.io.representation.OrderRepresentation;
import com.redbee.io.representation.VoteRepresentation;
import com.redbee.io.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by martin on 21/10/16.
 */
@RestController
@RequestMapping("/events/{id}/order")
public class OrderEventResource {

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.POST,value = "/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrderRepresentation create(@RequestBody OrderRepresentation orderRepresentation, @PathVariable("id") String id){
        return eventService.order(orderRepresentation,id);
    }
}
