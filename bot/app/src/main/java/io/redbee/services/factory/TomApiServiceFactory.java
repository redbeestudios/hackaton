package io.redbee.services.factory;

import io.redbee.services.TomApiServiceImpl;
import io.redbee.services.interfaces.TomApiService;

public class TomApiServiceFactory {
	
	
	
	 private TomApiServiceFactory() {}
	 
	 private static class TomApiServiceHolder { 
	        public static final TomApiService INSTANCE = new TomApiServiceImpl();
	    }

	    public static TomApiService getInstance() {
	        return TomApiServiceHolder.INSTANCE;
	    }
			

}
