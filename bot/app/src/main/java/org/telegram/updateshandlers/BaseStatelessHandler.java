package org.telegram.updateshandlers;

import static org.telegram.services.LocalisationService.lformat;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import org.telegram.Commands;
import org.telegram.SenderHelper;
import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.methods.Constants;
import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;
import org.telegram.api.objects.ReplyKeyboardHide;
import org.telegram.api.objects.ReplyKeyboardMarkup;
import org.telegram.api.objects.Update;
import org.telegram.services.BotLogger;
import org.telegram.services.Emoji;
import org.telegram.updateshandlers.states.CommandState;
import org.telegram.updateshandlers.states.DefaultCommandState;
import org.telegram.updatesreceivers.UpdatesThread;

/**
 * Created by herve on 4/17/16.
 */
public abstract class BaseStatelessHandler implements UpdatesCallback {

  public static final String DEFAULT_LANG = "es";
  private static final BotLogger LOGGER = BotLogger.getLogger(BaseStatelessHandler.class.getName());

  private CommandState currentState;

  public BaseStatelessHandler() {
    SenderHelper.SendWebhook("", Constants.BOT_TOKEN);
    new UpdatesThread(Constants.BOT_TOKEN, this);
    this.currentState = new DefaultCommandState();
  }

  @Override
  public void onUpdateReceived(Update update) {
	  LOGGER.info("Message: " + update.getMessage());
    try {
      BotApiMethod botApiMethod = handleIncomingMessage(Optional.ofNullable(update.getMessage()));
      SenderHelper.SendApiMethod(botApiMethod, Constants.BOT_TOKEN);
    } catch (final Exception e) {
      LOGGER.debug("Error handling the message", e);
    }

  }

  private BotApiMethod handleIncomingMessage(Optional<Message> optionalMessage) {
    Message message = optionalMessage.orElseThrow(RuntimeException::new);

    if (message.isGroupMessage() && message.hasText()) {
      if (message.getText().startsWith(Commands.STOPCOMMAND)) {
        return sendHideKeyboard(message.getFrom().getId(), message.getChatId(), message.getMessageId());
      }
    }
    return handleMessage(message);
  }

  public BotApiMethod handleMessage(Message message) {
    if (message.hasText()) {
      for (String[] action : getActions()) {
        if (message.getText().equalsIgnoreCase(lformat(action))) {

          try {
            Method handler = this.getClass().getMethod("handle" + capitalize(action[0]), Message.class);
            return (BotApiMethod)handler.invoke(this, message);
          } catch (Exception e) {
            LOGGER.error("Invocando handler", e);
            return buildErrorMsg(message);
          }

        }
      }
    }

    return buildErrorMsg(message);
  }

  private String capitalize(String str) {
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }

  public BotApiMethod buildErrorMsg(Message msg) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(msg.getChatId());
    sendMessage.enableMarkdown(true);
    sendMessage.setReplayToMessageId(msg.getMessageId());
    sendMessage.setText("Error " + Emoji.WAVING_HAND_SIGN.toString());
    sendMessage.setReplayMarkup(getDefaultKeyboard());

    return sendMessage;
  }

  public abstract List<String[]> getActions();

  public abstract ReplyKeyboardMarkup getDefaultKeyboard();

  @Override
  public BotApiMethod onWebhookUpdateReceived(Update update) {
    throw new UnsupportedOperationException();
  }


  private BotApiMethod sendHideKeyboard(Integer userId, Integer chatId, Integer messageId) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId);
    sendMessage.enableMarkdown(true);
    sendMessage.setReplayToMessageId(messageId);
    sendMessage.setText(Emoji.WAVING_HAND_SIGN.toString());

    ReplyKeyboardHide replyKeyboardHide = new ReplyKeyboardHide();
    replyKeyboardHide.setSelective(true);
    replyKeyboardHide.setHideKeyboard(true);
    sendMessage.setReplayMarkup(replyKeyboardHide);

    return sendMessage;
  }
}
