package org.telegram.updateshandlers.strategy;

import static org.telegram.services.LocalisationService.lformat;

import java.util.ArrayList;
import java.util.List;

import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;
import org.telegram.api.objects.ReplyKeyboardMarkup;
import org.telegram.services.Emoji;
import org.telegram.services.LocalisationService;

/**
 * Created by herve on 4/17/16.
 */
public class CurrentCommand implements Chooseable {

  private static final String ID = "current";

  @Override
  public boolean apply(Message message, String language) {
    return message.getText().equals(getCurrentCommand(language));
  }

  @Override
  public BotApiMethod get(Message message, String language) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);

    ReplyKeyboardMarkup replyKeyboardMarkup = getRecentsKeyboard(message.getFrom().getId(), language);
    sendMessage.setReplayMarkup(replyKeyboardMarkup);
    sendMessage.setReplayToMessageId(message.getMessageId());
    sendMessage.setChatId(message.getChatId());

    return sendMessage;
  }

  private static ReplyKeyboardMarkup getRecentsKeyboard(Integer userId, String language) {
    return getRecentsKeyboard(userId, language, true);
  }

  private static ReplyKeyboardMarkup getRecentsKeyboard(Integer userId, String language, boolean allowNew) {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboad(true);

    List<List<String>> keyboard = new ArrayList<>();

    List<String> row = new ArrayList<>();
    if (allowNew) {
      row.add(getLocationCommand(language));
      keyboard.add(row);

      row = new ArrayList<>();
      row.add(getNewCommand(language));
      keyboard.add(row);

      row = new ArrayList<>();
    }
    row.add(getCancelCommand(language));
    keyboard.add(row);

    replyKeyboardMarkup.setKeyboard(keyboard);

    return replyKeyboardMarkup;
  }

  private static String getNewCommand(String language) {
    return lformat("new", Emoji.HEAVY_PLUS_SIGN.toString());
  }

  private static String getLocationCommand(String language) {
    return lformat("location", Emoji.ROUND_PUSHPIN.toString());
  }

  private static String getCancelCommand(String language) {
    return lformat("cancel", Emoji.CROSS_MARK.toString());
  }

  private String getCurrentCommand(String language) {
    return lformat(CurrentCommand.ID, Emoji.BLACK_RIGHT_POINTING_TRIANGLE.toString());
  }
}
