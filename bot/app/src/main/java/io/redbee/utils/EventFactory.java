package io.redbee.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import io.redbee.domain.Event;
import io.redbee.domain.EventCancelled;
import io.redbee.domain.EventClosed;
import io.redbee.domain.EventOrdering;
import io.redbee.domain.EventVoting;

public class EventFactory {
	
	public static <T extends Event> T getEvent(Event event) throws InstantiationException, IllegalAccessException, InvocationTargetException{
		
		switch(event.getStatus()){
		
		case VOTING :
			return  cloneEvent(event, EventVoting.class);
		case ORDERING : 
			return  cloneEvent(event, EventOrdering.class);
		case CANCELLED :
			return  cloneEvent(event, EventCancelled.class);
		case CLOSED:
			return  cloneEvent(event, EventClosed.class);
		default:
			break;
		}
		
		
		return null;
	}

	private static <T extends Event> T cloneEvent(Event event, Class<?> subClass) throws InstantiationException, IllegalAccessException, InvocationTargetException {
		
		Event newBean = (Event) subClass.newInstance();
		
		
		BeanUtils.copyProperties(newBean, event);
		
		return (T) newBean;
	}

}
