package org.telegram.updateshandlers;

import static org.telegram.services.LocalisationService.lformat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.SenderHelper;
import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.methods.Constants;
import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;
import org.telegram.api.objects.ReplyKeyboardMarkup;
import org.telegram.api.objects.Update;
import org.telegram.services.BotLogger;
import org.telegram.services.Emoji;

import io.redbee.domain.Event;
import io.redbee.services.TomApiServiceImpl;
import io.redbee.services.factory.TomApiServiceFactory;
import io.redbee.services.interfaces.TomApiService;
import io.redbee.utils.GroupingCollector;
import redis.clients.jedis.Jedis;

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
