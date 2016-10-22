package io.redbee.domain;

import java.util.List;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {

  public enum Status {
      PENDING("PENDING"), VOTING("VOTING"), ORDERING("ORDERING"), CANCELLED("CANCELLED"), CLOSED("CLOSED");

      private String code;
      Status(String code) {
        this.code = code;
      }
  }

  @JsonProperty("id")
	private String eventId;
	private String name;
	private List<Restaurant> restaurants;
	private Status state;


	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	public Status getState() {
		return state;
	}
	public void setState(Status state) {
		this.state = state;
	}

  public BotApiMethod buildReplyMessage(Update update){
	return  null;
  }

  /**
   * Construye un mensaje de respuesta con un keyboard en base a un mensaje de entrada
   *
   * @param inputMsg
   * @param keyboard
   * @return
   */
  protected SendMessage buildMessage(Message inputMsg, ReplyKeyboard keyboard) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(inputMsg.getChatId().toString());
    sendMessage.enableMarkdown(true);
    sendMessage.setReplyToMessageId(inputMsg.getMessageId());
    sendMessage.setReplyMarkup(keyboard);
    return sendMessage;
  }
}
