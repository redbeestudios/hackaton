package io.redbee.domain;

public class Dish {
	
	private String dishId;
	private String restoId;
	private String description;
	private String type;
	
	public String getDishId() {
		return dishId;
	}
	public void setDishId(String dishId) {
		this.dishId = dishId;
	}
	public String getRestoId() {
		return restoId;
	}
	public void setRestoId(String restoId) {
		this.restoId = restoId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
