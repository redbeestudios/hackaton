package org.telegram.updateshandlers.states;

import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.objects.Message;

/**
 * Created by herve on 4/17/16.
 */
public interface CommandState{

  BotApiMethod execute(Message message, String language);

}
