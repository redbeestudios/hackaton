package org.telegram.updateshandlers;

import static org.telegram.services.LocalisationService.lformat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;
import org.telegram.api.objects.ReplyKeyboardMarkup;
import org.telegram.services.BotLogger;
import org.telegram.services.Emoji;
import org.telegram.services.JenkinsService;

/**
 * @author mbritez
 * @version 1.0
 * @brief Handler to show jenkins jobs status
 */
public class JenkinsHandlers extends BaseStatelessHandler {

  private static final BotLogger LOGGER = BotLogger.getLogger(JenkinsHandlers.class.getName());
  private List<String[]> actions = Arrays.asList(new String[]{"jobs", Emoji.CONSTRUCTION_SIGN.toString()},
    new String[]{"views", Emoji.TELEVISION.toString()}, new String[]{"commands", Emoji.BLACK_QUESTION_MARK_ORNAMENT.toString()});

  public JenkinsHandlers() {
    super();
  }

  @Override
  public ReplyKeyboardMarkup getDefaultKeyboard() {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboad(false);

    List<List<String>> keyboard = new ArrayList<>();
    List<String> keyboardFirstRow = new ArrayList<>();
    keyboardFirstRow.add(lformat(actions.get(0)));
    keyboardFirstRow.add(lformat(actions.get(1)));
    List<String> keyboardSecondRow = new ArrayList<>();
    keyboardSecondRow.add(lformat(actions.get(2)));
    keyboard.add(keyboardFirstRow);
    keyboard.add(keyboardSecondRow);
    replyKeyboardMarkup.setKeyboard(keyboard);

    return replyKeyboardMarkup;
  }

  private SendMessage buildMessage(Message inputMsg) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(inputMsg.getChatId());
    sendMessage.enableMarkdown(true);
    sendMessage.setReplayToMessageId(inputMsg.getMessageId());

    sendMessage.setReplayMarkup(getDefaultKeyboard());

    return sendMessage;
  }

  @SuppressWarnings("unused")
  public SendMessage handleJobs(Message message) {
    Map<String, Emoji> jobs = JenkinsService.getInstance().getJobs();

    StringBuffer text = new StringBuffer();

    for (Map.Entry<String, Emoji> entry : jobs.entrySet()) {
      text.append(entry.getValue()).append(" ").append(entry.getKey()).append('\n');
    }

    SendMessage result = buildMessage(message);
    result.setText(text.toString());

    return result;
  }

  @SuppressWarnings("unused")
  public SendMessage handleViews(Message message) {
    return buildMessage(message);
  }

  @SuppressWarnings("unused")
  public SendMessage handleCommands(Message message) {
    return buildMessage(message);
  }

  @Override
  public List<String[]> getActions() {
    return actions;
  }

@Override
protected BotApiMethod doHandleMessage(Message message) {
	// TODO Auto-generated method stub
	return null;
}
}