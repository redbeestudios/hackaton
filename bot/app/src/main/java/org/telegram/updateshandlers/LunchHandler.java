package org.telegram.updateshandlers;

import io.redbee.domain.Event;
import io.redbee.services.factory.TomApiServiceFactory;
import io.redbee.services.interfaces.TomApiService;

import org.telegram.SenderHelper;
import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.methods.Constants;
import org.telegram.api.objects.Update;
import org.telegram.services.BotLogger;

/**
 * Created by gustavo on 26/08/16.
 */
public class LunchHandler implements UpdatesCallback {

  private static final TomApiService tomApiService = TomApiServiceFactory.getInstance();

	private static final BotLogger LOGGER = BotLogger.getLogger(LunchHandler.class.getName());

	public LunchHandler() {
	}

  @Override
  public void onUpdateReceived(Update update) {
    Event event = tomApiService.findActiveEvent();

    try {
      BotApiMethod botApiMethod = event.buildReplyMessage(update.getMessage());

      SenderHelper.SendApiMethod(botApiMethod, Constants.BOT_TOKEN);
    } catch (Exception e) {
      LOGGER.error("Error manejando mensaje", e);
    }
  }

  @Override
  public BotApiMethod onWebhookUpdateReceived(Update update) {
    throw new UnsupportedOperationException();
  }
}
