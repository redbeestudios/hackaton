package io.redbee.domain;

import java.util.List;

import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;
import org.telegram.api.objects.ReplyKeyboardMarkup;

public class Event {

  public enum Status {
      PENDING("PENDING"), VOTING("VOTING"), ORDERING("ORDERING"), CANCELLED("CANCELLED"), CLOSED("CLOSED");

      private String code;
      Status(String code) {
        this.code = code;
      }
  }

	private String eventId;
	private String description;
	private List<Restaurant> restaurants;
	private Status status;


	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

  public  SendMessage buildReplyMessage(Message message){
	return  null;
  }

  /**
   * Construye un mensaje de respuesta con un keyboard en base a un mensaje de entrada
   *
   * @param inputMsg
   * @param keyboard
   * @return
   */
  protected SendMessage buildMessage(Message inputMsg, ReplyKeyboardMarkup keyboard) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(inputMsg.getChatId());
    sendMessage.enableMarkdown(true);
    sendMessage.setReplayToMessageId(inputMsg.getMessageId());
    sendMessage.setReplayMarkup(keyboard);
    return sendMessage;
  }
}
