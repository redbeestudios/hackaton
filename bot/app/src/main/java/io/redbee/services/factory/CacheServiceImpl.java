package io.redbee.services.factory;

import io.redbee.domain.Order;
import io.redbee.services.interfaces.CacheService;
import redis.clients.jedis.Jedis;

public class CacheServiceImpl implements CacheService {
	
	private Jedis jedis;
	
	public CacheServiceImpl(){
		jedis = new Jedis("localhost");
	}

	@Override
	public boolean deleteOrderEntries(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addOrderEntry(String key, Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Order getOrderEntry(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
