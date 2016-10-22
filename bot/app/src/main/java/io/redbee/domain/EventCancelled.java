package io.redbee.domain;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class EventCancelled extends Event {

  @Override
  public SendMessage buildReplyMessage(Message message) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(message.getChatId().toString());
    sendMessage.setReplyToMessageId(message.getMessageId());
    sendMessage.setText("El evento se canceló. Uaah!");

    return sendMessage;
  }
}
