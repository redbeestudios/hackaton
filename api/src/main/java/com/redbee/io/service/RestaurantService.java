package com.redbee.io.service;

import com.redbee.io.domain.Restaurant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 26/08/16.
 */
@Service
public class RestaurantService {


    public List<Restaurant> getAll() {
        List<Restaurant> result = new ArrayList<>();
        Restaurant item = new Restaurant();
        item.setName("Kentucky");
        item.setId("1");
        item.setDescription("The best pizza");
        result.add(item);
        return result;
    }
}
