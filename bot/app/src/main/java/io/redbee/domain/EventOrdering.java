package io.redbee.domain;

import java.util.ArrayList;
import java.util.List;

import io.redbee.services.factory.TomApiServiceFactory;
import io.redbee.services.interfaces.TomApiService;
import io.redbee.utils.GroupingCollector;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class EventOrdering extends Event {

  private static final TomApiService service = TomApiServiceFactory.getInstance();

  @Override
  public SendMessage buildReplyMessage(Message message) {

    Event event = service.findActiveEvent();
    List<Dish> dishes = service.findDishesForEvent(event.getEventId());

    SendMessage outgoingMsg = buildMessage(message, keyboard(event, dishes));
    outgoingMsg.setChatId(message.getChatId().toString());

    Dish votedDish = extractVotedDish(message, dishes);
    if (votedDish != null) {
      service.selectDishForEvent(event.getEventId(), votedDish.getDishId(), message.getFrom().getUserName());
      outgoingMsg.setText("Seleccionaste la siguiente comida " + message.getText());
    } else {
      outgoingMsg.setText("¿Qué comida querés?");
    }

    return outgoingMsg;

    }

  public Dish extractVotedDish(Message message, List<Dish> dishes) {
    if (message.hasText()) {
      for (Dish dish : dishes) {
        if (message.getText().equals(dish.getName())) return dish;
      }
    }

    return null;
  }

  public InlineKeyboardMarkup keyboard(Event event, List<Dish> dishes) {
//    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
//    replyKeyboardMarkup.setSelective(true);
//    replyKeyboardMarkup.setResizeKeyboard(true);
//    replyKeyboardMarkup.setOneTimeKeyboad(false);

    if (event.getState().equals(Status.ORDERING)) {

      List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();


      List<InlineKeyboardButton> row = null;

      List<List<Dish>> pages = dishes.stream().collect(new GroupingCollector<>(3));

      for(List<Dish> page : pages){

    	  row = new ArrayList();

	      for (Dish dish : page) {

          InlineKeyboardButton bt = new InlineKeyboardButton();
          bt.setText(dish.getName());
          bt.setCallbackData(dish.getName());
	        row.add(bt);

	      }
	      keyboard.add(row);
      }
      replyKeyboardMarkup.setKeyboard(keyboard);

    }
    return replyKeyboardMarkup;
  }
}
