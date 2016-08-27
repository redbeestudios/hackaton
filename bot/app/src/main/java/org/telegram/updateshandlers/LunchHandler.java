package org.telegram.updateshandlers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.SenderHelper;
import org.telegram.api.methods.Constants;
import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;
import org.telegram.api.objects.ReplyKeyboardMarkup;
import org.telegram.services.BotLogger;
import org.telegram.services.Emoji;

import io.redbee.Restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.telegram.services.LocalisationService.lformat;

/**
 * Created by gustavo on 26/08/16.
 */
public class LunchHandler extends BaseStatelessHandler {

    private static final BotLogger LOGGER = BotLogger.getLogger(JenkinsHandlers.class.getName());
    private List<String[]> actions = Arrays.asList(new String[]{"Restaurant", Emoji.CONSTRUCTION_SIGN.toString()},
            new String[]{"Event", Emoji.BUS_STOP.toString()}, new String[]{"Order", Emoji.DELIVERY_TRUCK.toString()},new String[]{"Poll", Emoji.TELEVISION.toString()});

    public LunchHandler(){
        super();
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

    public ReplyKeyboardMarkup getActionKeyboards(List<String[]> actions){

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboad(false);

        List<List<String>> keyboard = new ArrayList<>();

        List<String> keyboardFirstRow = new ArrayList<>();
        for (String[] action : actions) {
        	keyboardFirstRow.add(lformat(action));
		}
        
//        keyboardFirstRow.add(lformat(actions.get(1)));

//        List<String> keyboardSecondRow = new ArrayList<>();
//        keyboardSecondRow.add(lformat(actions.get(2)));

        keyboard.add(keyboardFirstRow);
//        keyboard.add(keyboardSecondRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;

    }

    private SendMessage buildMessage(Message inputMsg , ReplyKeyboardMarkup keyboard ) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(inputMsg.getChatId());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplayToMessageId(inputMsg.getMessageId());
        sendMessage.setReplayMarkup(keyboard);
        return sendMessage;
    }

    public SendMessage handleRestaurant(Message message) {
        
    	
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://demo5329197.mockable.io/restaurants");
    	
    	Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
    	
    	JSONArray restaurants = new JSONObject(response.readEntity(String.class)).getJSONArray("restaurants");
    	
    	List<String[]> restaurantActions = new ArrayList<>();
    	for (int i = 0; i < restaurants.length(); i++) {
    		JSONObject restaurant = restaurants.getJSONObject(i);
    		restaurantActions.add(new String[] {restaurant.getString("name"), Emoji.BLACK_NIB.toString()});
		}
    	
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
}
