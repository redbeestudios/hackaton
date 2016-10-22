package io.redbee.services;

import com.google.gson.Gson;

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
		
		Long resp = jedis.del(key);
		return (resp > 0);
	}

	@Override
	public boolean addOrderEntry(String key, Order order) {
		
		Gson gson = new Gson();
		String json = gson.toJson(order);
		String resp = jedis.set(key,json);
		
		
		
		return !resp.isEmpty();
	}

	@Override
	public Order getOrderEntry(String key) {
		Gson gson = new Gson();
		String json = jedis.get(key);
		Order object=gson.fromJson(json, Order.class);
		return object;
	}

}
