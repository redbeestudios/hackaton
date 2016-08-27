package org.telegram;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.methods.Constants;
import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;
import org.telegram.api.objects.Update;
import org.telegram.updateshandlers.UpdatesCallback;
import org.telegram.updatesreceivers.UpdatesThread;

public class HolaHandlers implements UpdatesCallback {

	public HolaHandlers() {
		SenderHelper.SendWebhook("", Constants.BOT_TOKEN);
		new UpdatesThread(Constants.BOT_TOKEN, this);

	}

	@Override
	public void onUpdateReceived(Update update) {

		System.out.println("dsdsd");

		Message message = update.getMessage();

		SendMessage sendMessage = new SendMessage();
		sendMessage.setText("Holaaa");
		sendMessage.setChatId(message.getChatId());
		// sendMessage.enableMarkdown(true);
		// sendMessage.setReplayToMessageId(message.getMessageId());

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://demo5329197.mockable.io/ttest").path("resource");

		Form form = new Form();
		form.param("x", "foo");
		form.param("y", "bar");

		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();

		System.out.println(response);

		SenderHelper.SendApiMethod(sendMessage, Constants.BOT_TOKEN);

	}

	@Override
	public BotApiMethod onWebhookUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		return null;
	}

}
