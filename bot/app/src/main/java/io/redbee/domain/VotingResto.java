package io.redbee.domain;

import java.io.Serializable;

public class VotingResto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2254353159190037959L;
	
	private String restaurantId;
	private String userId;
	
	public  VotingResto(String restaurantId, String userId){
		this.restaurantId = restaurantId;
		this.userId = userId;
		
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
