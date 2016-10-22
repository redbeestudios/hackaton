package io.redbee.services.interfaces;

import io.redbee.domain.Order;

public interface CacheService {
	
	public boolean deleteOrderEntries(String key);
	
	public boolean addOrderEntry(String key, Order order);
	
	public Order getOrderEntry(String key);
}
