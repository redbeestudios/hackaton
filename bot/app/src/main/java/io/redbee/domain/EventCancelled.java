package io.redbee.domain;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public class EventCancelled extends Event {

  @Override
  public BotApiMethod buildReplyMessage(Update update) {
    Message message = update.getMessage();
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(message.getChatId().toString());
    sendMessage.setReplyToMessageId(message.getMessageId());
    sendMessage.setText("El evento se canceló. Uaah!");

    return sendMessage;
  }
}
