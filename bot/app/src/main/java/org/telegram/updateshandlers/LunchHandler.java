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

import io.redbee.utils.GroupingCollector;
import redis.clients.jedis.Jedis;

/**
 * Created by gustavo on 26/08/16.
 */
public class LunchHandler extends BaseStatelessHandler {

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

	private SendMessage buildMessage(Message inputMsg, ReplyKeyboardMarkup keyboard) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(inputMsg.getChatId());
		sendMessage.enableMarkdown(true);
		sendMessage.setReplayToMessageId(inputMsg.getMessageId());
		sendMessage.setReplayMarkup(keyboard);
		return sendMessage;
	}

	public SendMessage handleRestaurant(Message message) {

		WebTarget target = client.target("http://54.208.135.33:8081/restaurants");

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

	public SendMessage handleEvent(Message message) {
		return buildMessage(message, getDefaultKeyboard());
	}

	public SendMessage handlePoll(Message message) {
		return buildMessage(message, getDefaultKeyboard());
	}

	public SendMessage handleOrder(Message message) {
		return buildMessage(message, getDefaultKeyboard());
	}

	@Override
	protected BotApiMethod doHandleMessage(Message message) {

		
		WebTarget target = client.target("http://54.208.135.33:8081/restaurants");
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
		JSONArray restaurants = new JSONArray(response.readEntity(String.class));

		String level = jedis.get(message.getChatId().toString());

		List<String[]> dishesActions = new ArrayList<>();
		if (level.equals("1")) {
			for (int i = 0; i < restaurants.length(); i++) {
				JSONObject restaurant = restaurants.getJSONObject(i);
				if (restaurant.getString("name").equals(message.getText())) {
					JSONArray dishes = restaurant.getJSONArray("dishes");

					for (int j = 0; j < dishes.length(); j++) {
						JSONObject dish = dishes.getJSONObject(j);
						dishesActions.add(new String[] { dish.getString("type"), Emoji.BLACK_NIB.toString() });
					}

				}
			}
		}

		if (!dishesActions.isEmpty()) {

			jedis.set(message.getChatId().toString(), "2");

			SendMessage sendMessage = new SendMessage();
			sendMessage.setText("Testttt");
			sendMessage.setChatId(message.getChatId());
			sendMessage.setReplayMarkup(getActionKeyboards(dishesActions));

			return sendMessage;
		}
		return buildErrorMsg(message);
	}
}
