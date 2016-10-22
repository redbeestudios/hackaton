package io.redbee.domain;

import static org.telegram.services.LocalisationService.lformat;

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

    SendMessage replyMessage = buildMessage(message, keyboard());
    replyMessage.setText("A que lugar le pedimos comida?");
    replyMessage.setChatId(message.getChatId());

    return replyMessage;
  }

  public ReplyKeyboardMarkup keyboard() {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboad(false);

    Event event = service.findActiveEvent();

    List<Restaurant> restaurants = service.findRestaurants(event.getEventId());

    List<List<String>> keyboard = new ArrayList<>();
    List<String> keyboardFirstRow = new ArrayList<>();

    for (Restaurant restaurant : restaurants) {

      keyboardFirstRow.add(restaurant.getDescription());

    }
    keyboard.add(keyboardFirstRow);

    replyKeyboardMarkup.setKeyboard(keyboard);

    return replyKeyboardMarkup;
  }
}
