package io.redbee;

import io.redbee.domain.Event;
import io.redbee.services.factory.TomApiServiceFactory;
import io.redbee.services.interfaces.TomApiService;

import org.telegram.telegrambots.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.logging.BotLogger;


/**
 * Created by gustavo on 26/08/16.
 */
public class LunchHandler extends TelegramLongPollingBot {

  private static final TomApiService tomApiService = TomApiServiceFactory.getInstance();

//	private static final BotLogger LOGGER = BotLogger.getLogger(LunchHandler.class.getName());

	public LunchHandler() {
	}

  @Override
  public void onUpdateReceived(Update update) {
    Event event = tomApiService.findActiveEvent();

    try {
      BotApiMethod botApiMethod = event.buildReplyMessage(update);

      if (botApiMethod instanceof AnswerCallbackQuery) {
        answerCallbackQuery((AnswerCallbackQuery) botApiMethod);
      }
      if (botApiMethod instanceof SendMessage) {
        sendMessage((SendMessage) botApiMethod);
      }
      if (botApiMethod instanceof EditMessageText) {
        editMessageText((EditMessageText) botApiMethod);
      }

    } catch (Exception e) {
      BotLogger.error("Error manejando mensaje", e);
    }
  }

  @Override
  public String getBotUsername() {
    return "rbstestbot";
  }


  @Override
  public String getBotToken() {
    return "49873302:AAEuu-gLv8O_zBY7aPmi_CHgggeLwALFmZA";
  }
}
