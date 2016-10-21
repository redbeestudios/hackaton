package io.redbee;

import org.telegram.SenderHelper;
import org.telegram.api.methods.Constants;
import org.telegram.updateshandlers.LunchHandler;
import org.telegram.updateshandlers.UpdatesCallback;
import org.telegram.updatesreceivers.UpdatesThread;

public class Main {

    public static void main(String[] args) {
      UpdatesThread updatesThread = new UpdatesThread(Constants.BOT_TOKEN, new LunchHandler());
      SenderHelper.SendWebhook("", Constants.BOT_TOKEN);
    }

}
