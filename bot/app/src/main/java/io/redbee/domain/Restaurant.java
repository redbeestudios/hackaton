package io.redbee.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Restaurant {

  @JsonProperty("id")
  private String restaurantId;
  private String name;
  private List<Dish> dishes;

  public List<Dish> getDishes() {
    return dishes;
  }

  public void setDishes(List<Dish> dishes) {
    this.dishes = dishes;
  }

  public String getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(String restaurantId) {
    this.restaurantId = restaurantId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
