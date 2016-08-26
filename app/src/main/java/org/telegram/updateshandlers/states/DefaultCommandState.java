package org.telegram.updateshandlers.states;

import static org.telegram.services.LocalisationService.lformat;

import java.util.ArrayList;
import java.util.List;

import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;
import org.telegram.api.objects.ReplyKeyboardMarkup;
import org.telegram.services.Emoji;
import org.telegram.services.JenkinsService;
import org.telegram.services.LocalisationService;

/**
 * Created by herve on 4/17/16.
 */
public class DefaultCommandState implements CommandState {

  @Override
  public BotApiMethod execute(Message message, String language) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);

    ReplyKeyboardMarkup mainMenuKeyboard = getKeyboard(language);
    sendMessage.setReplayMarkup(mainMenuKeyboard);
    sendMessage.setReplayToMessageId(message.getMessageId());
    sendMessage.setChatId(message.getChatId());
    sendMessage.setText("Los jobs de jenkins son: \n" + JenkinsService.getInstance().getJobs());

    return sendMessage;
  }

  protected ReplyKeyboardMarkup getKeyboard(String language) {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboad(false);

    List<List<String>> keyboard = new ArrayList<>();
    List<String> keyboardFirstRow = new ArrayList<>();
    keyboardFirstRow.add(getMainCommand(language));
    keyboard.add(keyboardFirstRow);
    replyKeyboardMarkup.setKeyboard(keyboard);

    return replyKeyboardMarkup;
  }

  private String getMainCommand(String language) {
    return lformat("current", Emoji.BLACK_RIGHT_POINTING_TRIANGLE.toString());
  }
}
