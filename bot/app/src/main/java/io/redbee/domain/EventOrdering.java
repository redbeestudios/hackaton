package io.redbee.domain;

import java.util.ArrayList;
import java.util.List;

import io.redbee.services.factory.TomApiServiceFactory;
import io.redbee.services.interfaces.TomApiService;

import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;
import org.telegram.api.objects.ReplyKeyboardMarkup;

public class EventOrdering extends Event {

  private static final TomApiService service = TomApiServiceFactory.getInstance();

  @Override
  public SendMessage buildReplyMessage(Message message) {

    Event event = service.findActiveEvent();
    List<Dish> dishes = service.findDishesForEvent(event.getEventId());

    SendMessage outgoingMsg = buildMessage(message, keyboard(event, dishes));
    outgoingMsg.setChatId(message.getChatId());

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

  public ReplyKeyboardMarkup keyboard(Event event, List<Dish> dishes) {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboad(false);

    if (event.getStatus().equals(Status.ORDERING)) {

      List<List<String>> keyboard = new ArrayList<>();
      List<String> keyboardFirstRow = new ArrayList<>();

      for (Dish dish : dishes) {

        keyboardFirstRow.add(dish.getName());

      }
      keyboard.add(keyboardFirstRow);

      replyKeyboardMarkup.setKeyboard(keyboard);

    }
    return replyKeyboardMarkup;
  }
}
