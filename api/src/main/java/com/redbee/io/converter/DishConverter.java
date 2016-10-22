package com.redbee.io.converter;

import com.redbee.io.persistence.entities.Dish;
import com.redbee.io.persistence.entities.Order;
import com.redbee.io.representation.DishRepresentation;
import com.redbee.io.representation.OrderRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fabrizio on 21/10/16.
 */
@Component
public class DishConverter {

    public DishRepresentation convert(Dish dish){
        DishRepresentation result = new DishRepresentation();
        result.setId(dish.getId());
        result.setType(dish.getType());
        result.setName(dish.getName());
        return result;
    }

    public Dish convert(DishRepresentation dishRepresentation) {
        Dish result = new Dish();
        result.setId(dishRepresentation.getId());
        result.setName(dishRepresentation.getName());
        result.setType(dishRepresentation.getType());
        return result;
    }

    public List<DishRepresentation> convertList(List<Dish> all) {
        return all.stream().map(this::convert).collect(Collectors.toList());
    }

    public List<Dish> convertListRepresentation(List<DishRepresentation> all) {
        return all.stream().map(this::convert).collect(Collectors.toList());
    }
}