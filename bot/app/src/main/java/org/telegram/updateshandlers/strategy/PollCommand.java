package org.telegram.updateshandlers.strategy;

import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.objects.Message;
import org.telegram.services.Emoji;

import static org.telegram.services.LocalisationService.lformat;

/**
 * Created by herve on 4/17/16.
 */
public class PollCommand implements Chooseable {

  private static final String ID = "poll";


  @Override
  public boolean apply(Message message, String language) {
    return message.getText().equals(getCurrentCommand(language));
  }

  @Override
  public BotApiMethod get(Message message, String language) {

    return null;
  }

  private String getCurrentCommand(String language) {
    return lformat(PollCommand.ID, Emoji.BLACK_RIGHT_POINTING_TRIANGLE.toString());
  }
}
