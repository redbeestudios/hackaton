package io.redbee.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Dish {

  @JsonIgnore
	private String dishId;
	private String name;
	private String type;
	
	public String getDishId() {
		return dishId;
	}
	public void setDishId(String dishId) {
		this.dishId = dishId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
