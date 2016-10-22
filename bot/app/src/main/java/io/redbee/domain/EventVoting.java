package io.redbee.domain;

import java.util.ArrayList;
import java.util.List;

import io.redbee.services.factory.TomApiServiceFactory;
import io.redbee.services.interfaces.TomApiService;
import io.redbee.utils.GroupingCollector;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;


public class EventVoting extends Event {

  private static final TomApiService service = TomApiServiceFactory.getInstance();

  @Override
  public BotApiMethod buildReplyMessage(Update update) {
    Message message = update.getMessage();

    Event event = service.findActiveEvent();
    List<Restaurant> restaurants = service.findRestaurants(event.getEventId());

    SendMessage outgoingMsg = buildMessage(message, keyboard(restaurants));
    outgoingMsg.setChatId(message.getChatId().toString());

    Restaurant votedRestaurant = extractVotedRestaurant(message, restaurants);
    if (votedRestaurant != null) {
      service.voteRestaurantForEvent(event.getEventId(), votedRestaurant.getRestaurantId(), message.getFrom().getUserName());
      outgoingMsg.setText("Tu voto está registrado ahora en " + message.getText());
    } else {
      outgoingMsg.setText("¿A qué lugar le pedimos comida?");
    }

    return outgoingMsg;
  }

  public Restaurant extractVotedRestaurant(Message message, List<Restaurant> restaurants) {
    if (message.hasText()) {
      for (Restaurant restaurant : restaurants) {
        if (message.getText().equals(restaurant.getName())) return restaurant;
      }
    }

    return null;
  }

  public ReplyKeyboardMarkup keyboard(List<Restaurant> restaurants) {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboad(false);

    List<KeyboardRow> keyboard = new ArrayList<>();
    KeyboardRow row = null;
    
    List<List<Restaurant>> pages = restaurants.stream().collect(new GroupingCollector<>(3));

    for(List<Restaurant> page : pages){
    	
    	row = new KeyboardRow();
    
	    for (Restaurant restaurant : page) {
	
	      row.add(restaurant.getName());
	
	    }

	    keyboard.add(row);
    
    }

    replyKeyboardMarkup.setKeyboard(keyboard);

    return replyKeyboardMarkup;
  }
}
