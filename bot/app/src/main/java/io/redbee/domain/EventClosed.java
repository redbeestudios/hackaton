package io.redbee.domain;

import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;

public class EventClosed extends Event {

  @Override
  public SendMessage buildReplyMessage(Message message) {

    SendMessage outgoingMsg = new SendMessage();
    outgoingMsg.setChatId(message.getChatId());
    outgoingMsg.setReplayToMessageId(message.getMessageId());
    outgoingMsg.setText("El evento se cerró. Uaah!");

    return outgoingMsg;
  }

}
