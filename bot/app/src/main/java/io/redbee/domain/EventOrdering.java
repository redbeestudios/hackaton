package io.redbee.domain;

import java.util.ArrayList;
import java.util.List;

import io.redbee.services.factory.TomApiServiceFactory;
import io.redbee.services.interfaces.TomApiService;
import io.redbee.utils.GroupingCollector;

import org.telegram.telegrambots.api.methods.AnswerCallbackQuery;
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

      EditMessageText edit = new EditMessageText();
      edit.setText("Tu pedido es 20% mejor ahora");
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
//    if (votedDish != null) {
//      service.selectDishForEvent(event.getEventId(), votedDish.getDishId(), message.getFrom().getUserName());
//      outgoingMsg.setText("Seleccionaste la siguiente comida " + message.getText());
//    } else {
//    }
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
    InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();

    if (event.getState().equals(Status.ORDERING)) {

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
