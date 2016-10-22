package com.redbee.io.resources;

import com.redbee.io.representation.VoteRepresentation;
import com.redbee.io.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by martin on 21/10/16.
 */
@RestController
@RequestMapping("/events/{id}/resto")
public class VoteEventRestaurantResource {

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.POST,value = "/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public VoteRepresentation create(@RequestBody VoteRepresentation voteRepresentation, @PathVariable("id") String id){
        return eventService.vote(voteRepresentation,id);
    }
}
