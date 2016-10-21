package org.telegram.updateshandlers;

import static org.telegram.services.LocalisationService.lformat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;
import org.telegram.api.objects.ReplyKeyboardMarkup;
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
public class LunchHandler extends BaseStatelessHandler {

  private static final TomApiService tomApiService = TomApiServiceFactory.getInstance();

	private static final BotLogger LOGGER = BotLogger.getLogger(LunchHandler.class.getName());
	private List<String[]> actions = Arrays.asList(new String[] { "Restaurant", Emoji.CONSTRUCTION_SIGN.toString() },
			new String[] { "Event", Emoji.BUS_STOP.toString() },
			new String[] { "Order", Emoji.DELIVERY_TRUCK.toString() },
			new String[] { "Poll", Emoji.TELEVISION.toString() });

	private Jedis jedis;
	private Client client;

	public LunchHandler() {
		super();
		jedis = new Jedis("172.30.0.162");
		client = ClientBuilder.newClient();
	}

	@Override
	public List<String[]> getActions() {

		return this.actions;
	}

	@Override
	public ReplyKeyboardMarkup getDefaultKeyboard() {
    Event event = null; //tomApiService.find...

    if (event == null) {
      return keyboardNullEvent();
    }

    if (event.getStatus().equals(Event.Status.ORDERING)) {
      return keyboardOrdering();
    }

    return keyboardNullEvent();
	}

  public ReplyKeyboardMarkup keyboardNullEvent() {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboad(false);
    List<List<String>> keyboard = new ArrayList<>();
    List<String> keyboardFirstRow = new ArrayList<>();
    keyboardFirstRow.add(lformat(this.actions.get(0)));
    keyboardFirstRow.add(lformat(this.actions.get(1)));
    List<String> keyboardSecondRow = new ArrayList<>();
    keyboardSecondRow.add(lformat(this.actions.get(2)));
    keyboardSecondRow.add(lformat(this.actions.get(3)));
    keyboard.add(keyboardFirstRow);
    keyboard.add(keyboardSecondRow);
    replyKeyboardMarkup.setKeyboard(keyboard);

    return replyKeyboardMarkup;
  }



  public ReplyKeyboardMarkup keyboardOrdering() {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboad(false);
    List<List<String>> keyboard = new ArrayList<>();
    List<String> keyboardFirstRow = new ArrayList<>();
    keyboardFirstRow.add(lformat(this.actions.get(0)));
    keyboardFirstRow.add(lformat(this.actions.get(1)));
    List<String> keyboardSecondRow = new ArrayList<>();
    keyboardSecondRow.add(lformat(this.actions.get(2)));
    keyboardSecondRow.add(lformat(this.actions.get(3)));
    keyboard.add(keyboardFirstRow);
    keyboard.add(keyboardSecondRow);
    replyKeyboardMarkup.setKeyboard(keyboard);

    return replyKeyboardMarkup;
  }

	public ReplyKeyboardMarkup getActionKeyboards(List<String[]> actions) {

		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

		replyKeyboardMarkup.setSelective(true);
		replyKeyboardMarkup.setResizeKeyboard(true);
		replyKeyboardMarkup.setOneTimeKeyboad(false);

		List<List<String>> keyboard = new ArrayList<>();

		List<List<String[]>> pages = actions.stream().collect(new GroupingCollector<>(3));

		List<String> keyboardFirstRow = new ArrayList<>(0);

		for (List<String[]> page : pages) {
			keyboardFirstRow = new ArrayList<>();
			for (String[] action : page) {

				keyboardFirstRow.add(lformat(action));
			}
			keyboard.add(keyboardFirstRow);
		}

		replyKeyboardMarkup.setKeyboard(keyboard);

		return replyKeyboardMarkup;

	}

	public SendMessage handleRestaurant(Message message) {

		WebTarget target = client.target("http://demo5329197.mockable.io/restaurants");

		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();

		JSONArray restaurants = new JSONArray(response.readEntity(String.class));

		List<String[]> restaurantActions = new ArrayList<>();
		for (int i = 0; i < restaurants.length(); i++) {
			JSONObject restaurant = restaurants.getJSONObject(i);
			restaurantActions.add(new String[] { restaurant.getString("name"), "" });
		}

		jedis.set(message.getChatId().toString(), "1");

		SendMessage sendMessage = new SendMessage();
		sendMessage.setText("Testttt");
		sendMessage.setChatId(message.getChatId());
		sendMessage.setReplayMarkup(getActionKeyboards(restaurantActions));

		return sendMessage;
	}

	@Override
	protected BotApiMethod doHandleMessage(Message message) {

    Event event = tomApiService.findActiveEvent();

    return event.buildReplyMessage(message);

	}
}
