package io.redbee.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.redbee.services.factory.CacheServiceFactory;
import io.redbee.services.factory.TomApiServiceFactory;
import io.redbee.services.interfaces.CacheService;
import io.redbee.services.interfaces.TomApiService;
import io.redbee.utils.GroupingCollector;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class EventOrdering extends Event {

  private static final TomApiService service = TomApiServiceFactory.getInstance();
  private static final CacheService cache = CacheServiceFactory.getInstance();

  @Override
  public BotApiMethod buildReplyMessage(Update update) {
    Message message = update.getMessage();

    Event event = service.findActiveEvent();
    List<Dish> dishes = service.findDishesForEvent(event.getEventId());

    if (update.hasCallbackQuery()) {
      // ACA entra si se hizo click en un boton inline
      CallbackQuery callbackQuery = update.getCallbackQuery();

//      AnswerCallbackQuery answer = new AnswerCallbackQuery();
//      answer.setCallbackQueryId(callbackQuery.getId());
//      answer.setText("+1 " + callbackQuery.getData());
//
//      return answer;

      Order order = updateOrder(callbackQuery, event);

      EditMessageText edit = new EditMessageText();
      edit.setText(buildOrderMessage(callbackQuery, event));
      edit.setChatId(String.valueOf(callbackQuery.getMessage().getChatId()));
      edit.setMessageId(callbackQuery.getMessage().getMessageId());
      edit.setInlineMessageId(callbackQuery.getInlineMessageId());
      edit.setReplyMarkup(keyboard(event, dishes));

      return edit;

    } else {
      SendMessage outgoingMsg = buildMessage(message, keyboard(event, dishes));
      outgoingMsg.setChatId(message.getChatId().toString());
      outgoingMsg.setText("¿Qué comida querés?");

      return outgoingMsg;
    }
  }

  private Order updateOrder(CallbackQuery callbackQuery, Event event) {
    String key = buildKey(callbackQuery.getMessage(), event);
    Order order = getOrder(callbackQuery, event);

    String data = callbackQuery.getData();
    if (Dish.BORRAR.getName().equals(data)) {
      order.getDishes().clear();
    } else {
      order.getDishes().add(data);
    }

    cache.addOrderEntry(key, order);

    return order;
  }

  private String buildOrderMessage(CallbackQuery callbackQuery, Event event) {
    Order order = getOrder(callbackQuery, event);


    StringBuffer message = new StringBuffer();
    if (order.getDishes() == null || order.getDishes().size() == 0)
      message.append("Su pedido está vacío");

    HashMap<String, Integer> dishes = new HashMap<>();

    for (String dishname : order.getDishes()) {
      if (!dishes.containsKey(dishname))
        dishes.put(dishname, 1);
      else
        dishes.put(dishname, dishes.get(dishname) + 1);
    }

    for (Map.Entry<String, Integer> entry : dishes.entrySet()) {
      message.append(entry.getValue()).append(" ").append(entry.getKey()).append("\n");
    }

    return message.toString();
  }

  private Order getOrder(CallbackQuery callbackQuery, Event event) {
    String key = buildKey(callbackQuery.getMessage(), event);
    Order order = cache.getOrderEntry(key);

    if (order == null) {
      cache.addOrderEntry(key, new Order(new ArrayList<String>(), null));
    }

    return order;
  }

  private String buildKey(Message message, Event event) {
    return message.getChatId() + "-" + event.getName();
  }

  public InlineKeyboardMarkup keyboard(Event event, List<Dish> dishes) {
    InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

    if (event.getState().equals(Status.ORDERING)) {

      dishes.add(Dish.BORRAR);
      dishes.add(Dish.ENVIAR);

      List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

      List<InlineKeyboardButton> row = null;

      List<List<Dish>> pages = dishes.stream().collect(new GroupingCollector<>(3));

      for (List<Dish> page : pages) {

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
