package io.redbee.domain;

import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;

public class EventCancelled extends Event {

  @Override
  public SendMessage buildReplyMessage(Message message) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(message.getChatId());
    sendMessage.setReplayToMessageId(message.getMessageId());
    sendMessage.setText("El evento se canceló. Uaah!");

    return sendMessage;
  }
}
