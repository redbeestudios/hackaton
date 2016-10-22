package io.redbee.services.factory;

import io.redbee.services.CacheServiceImpl;
import io.redbee.services.interfaces.CacheService;

public class CacheServiceFactory {
	
	
	
	 private CacheServiceFactory() {}
	 
	 private static class CacheServiceHolder { 
	        public static final CacheService INSTANCE = new CacheServiceImpl();
	    }

	    public static CacheService getInstance() {
	        return CacheServiceHolder.INSTANCE;
	    }
			

}
