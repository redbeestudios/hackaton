package com.redbee.io.resources;

import com.redbee.io.persistence.entities.Restaurant;
import com.redbee.io.representation.RestaurantRepresentation;
import com.redbee.io.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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

    @RequestMapping( method = GET,value = "/{id}")
    public Restaurant getRestaurant(@PathVariable("id")  String id){
        return restaurantService.get(id);
    }


    @RequestMapping(method = POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public RestaurantRepresentation create(@RequestBody RestaurantRepresentation restaurant){
        return restaurantService.create(restaurant);
    }

    @RequestMapping(method = PUT,value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody RestaurantRepresentation restaurant,@PathVariable("id") String id) {
        restaurantService.update(restaurant,id);
    }

    @RequestMapping(method = DELETE,value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("id") String id){
        restaurantService.delete(id);
    }

}
