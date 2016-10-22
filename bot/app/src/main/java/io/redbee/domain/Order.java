package io.redbee.domain;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 3539380698189611928L;

	private List<String> dishes;
	private String userId;
  private boolean submitted = false;

  public Order() {}

	public Order(List<String> dishes, String userId) {
		
		this.dishes = dishes;
		this.userId =  userId;
		
	}

	public List<String> getDishes() {
		return dishes;
	}

	public void setDishes(List<String> dishes) {
		this.dishes = dishes;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

  public boolean isSubmitted() {
    return submitted;
  }

  public void setSubmitted(boolean submitted) {
    this.submitted = submitted;
  }
}
