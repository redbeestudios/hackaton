package org.telegram.updateshandlers.strategy;

import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.objects.Message;

/**
 * Created by herve on 4/17/16.
 */
public class MainMenuCommand implements Chooseable {

  @Override
  public boolean apply(Message message, String language) {
    return false;
  }

  @Override
  public BotApiMethod get(Message message, String language) {
    return null;
  }
}
