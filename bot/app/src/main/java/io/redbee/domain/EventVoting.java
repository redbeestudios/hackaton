package io.redbee.domain;

import java.util.ArrayList;
import java.util.List;

import io.redbee.services.factory.TomApiServiceFactory;
import io.redbee.services.interfaces.TomApiService;
import io.redbee.utils.GroupingCollector;

import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;
import org.telegram.api.objects.ReplyKeyboardMarkup;

public class EventVoting extends Event {

  private static final TomApiService service = TomApiServiceFactory.getInstance();

  @Override
  public SendMessage buildReplyMessage(Message message) {

    Event event = service.findActiveEvent();
    List<Restaurant> restaurants = service.findRestaurants(event.getEventId());

    SendMessage outgoingMsg = buildMessage(message, keyboard(restaurants));
    outgoingMsg.setChatId(message.getChatId());

    if (messageIsVote(message, restaurants)) {
      outgoingMsg.setText("Tu voto está registrado ahora en " + message.getText());
    } else {
      outgoingMsg.setText("¿A qué lugar le pedimos comida?");
    }

    return outgoingMsg;
  }

  public boolean messageIsVote(Message message, List<Restaurant> restaurants) {
    if (message.hasText()) {
      for (Restaurant restaurant : restaurants) {
        if (message.getText().equals(restaurant.getDescription())) return true;
      }
    }

    return false;
  }

  public ReplyKeyboardMarkup keyboard(List<Restaurant> restaurants) {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboad(false);

    List<List<String>> keyboard = new ArrayList<>();
    List<String> keyboardFirstRow = null;
    
    List<List<Restaurant>> pages = restaurants.stream().collect(new GroupingCollector<>(3));

    for(List<Restaurant> page : pages){
    	
    	keyboardFirstRow = new ArrayList<>();
    
	    for (Restaurant restaurant : page) {
	
	      keyboardFirstRow.add(restaurant.getDescription());
	
	    }

	    keyboard.add(keyboardFirstRow);
    
    }

    replyKeyboardMarkup.setKeyboard(keyboard);

    return replyKeyboardMarkup;
  }
}
