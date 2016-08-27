package com.redbee.io.resources;

import com.redbee.io.domain.Restaurant;
import com.redbee.io.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
}
