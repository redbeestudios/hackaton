package com.redbee.io.resources;

import com.redbee.io.domain.Restaurant;
import com.redbee.io.representation.RestaurantRepresentation;
import com.redbee.io.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by Channo  26/08/16.
 */
@RestController
@RequestMapping("/restaurants")
public class RestaurantResource {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantResource(RestaurantService service) {
        this.restaurantService = service;
    }

    @RequestMapping( method = GET)
    public List<Restaurant> list(){
        return restaurantService.getAll();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody RestaurantRepresentation restaurant){
        restaurantService.create(restaurant);

    }

    @RequestMapping(method = PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody RestaurantRepresentation restaurant) {
        restaurantService.update(restaurant);
    }
}
