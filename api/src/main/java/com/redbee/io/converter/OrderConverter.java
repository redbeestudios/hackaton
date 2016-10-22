package com.redbee.io.converter;

import com.redbee.io.persistence.entities.Order;
import com.redbee.io.representation.OrderRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by fabrizio on 21/10/16.
 */
@Component
public class OrderConverter {


    private DishConverter dishConverter;
    private UserConverter userConverter;

    @Autowired
    public OrderConverter(UserConverter userConverter,DishConverter dishConverter) {
        this.userConverter = userConverter;
        this.dishConverter = dishConverter;
    }

    public OrderRepresentation convert(Order order){
        OrderRepresentation result = new OrderRepresentation();
        result.setId(order.getId());
        result.setDish(dishConverter.convert(order.getDish()));
        result.setUser(userConverter.convert(order.getUser()));
        return result;
    }

    public Order convertrepresentation(OrderRepresentation orderRepresentation) {
        Order result = new Order();
        result.setId(orderRepresentation.getId());
        result.setUser(userConverter.convert(orderRepresentation.getUser()));
        result.setDish(dishConverter.convert(orderRepresentation.getDish()));
        return result;
    }

    public List<OrderRepresentation> convertList(List<Order> orders) {
        if (orders != null){
            return orders.stream().map(this::convert).collect(Collectors.toList());
        }else {
            return new ArrayList<>();
        }
    }

    public List<Order> convertListRepresentation(List<OrderRepresentation> orders) {
        if (orders != null){
            return orders.stream().map(this::convertrepresentation).collect(Collectors.toList());
        }else {
            return new ArrayList<>();
        }
    }
}