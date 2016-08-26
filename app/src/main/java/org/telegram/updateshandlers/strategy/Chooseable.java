package org.telegram.updateshandlers.strategy;

import org.telegram.api.methods.BotApiMethod;
import org.telegram.api.objects.Message;

/**
 * Created by herve on 4/17/16.
 */
public interface Chooseable {

  boolean apply(final Message message, final String language);

  BotApiMethod get(Message message, String language);

}
