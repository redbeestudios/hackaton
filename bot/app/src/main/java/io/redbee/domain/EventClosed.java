package io.redbee.domain;


import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class EventClosed extends Event {

  @Override
  public SendMessage buildReplyMessage(Message message) {

    SendMessage outgoingMsg = new SendMessage();
    outgoingMsg.setChatId(message.getChatId().toString());
    outgoingMsg.setReplyToMessageId(message.getMessageId());
    outgoingMsg.setText("El evento se cerró. Uaah!");

    return outgoingMsg;
  }

}
