package io.redbee;

import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {
//      UpdatesThread updatesThread = new UpdatesThread(Constants.BOT_TOKEN, new LunchHandler());
//      SenderHelper.SendWebhook("", Constants.BOT_TOKEN);


      TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
      try {
        telegramBotsApi.registerBot(new LunchHandler());
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }

}
