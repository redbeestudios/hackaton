package io.redbee.domain;


import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public class EventClosed extends Event {

  @Override
  public BotApiMethod buildReplyMessage(Update update) {
    Message message = update.getMessage();

    SendMessage outgoingMsg = new SendMessage();
    outgoingMsg.setChatId(message.getChatId().toString());
    outgoingMsg.setReplyToMessageId(message.getMessageId());
    outgoingMsg.setText("El evento se cerró. Uaah!");

    return outgoingMsg;
  }

}
