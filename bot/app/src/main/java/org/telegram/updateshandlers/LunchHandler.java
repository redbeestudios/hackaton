package org.telegram.updateshandlers;

import org.telegram.api.methods.SendMessage;
import org.telegram.api.objects.Message;
import org.telegram.api.objects.ReplyKeyboardMarkup;
import org.telegram.services.BotLogger;
import org.telegram.services.Emoji;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.telegram.services.LocalisationService.lformat;

/**
 * Created by gustavo on 26/08/16.
 */
public class LunchHandler extends BaseStatelessHandler {

    private static final BotLogger LOGGER = BotLogger.getLogger(JenkinsHandlers.class.getName());
    private List<String[]> actions = Arrays.asList(new String[]{"event", Emoji.CONSTRUCTION_SIGN.toString()},
            new String[]{"poll", Emoji.TELEVISION.toString()}, new String[]{"orders", Emoji.BICYCLE.toString()});

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
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getPollKeyBoard(){

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

    private SendMessage buildMessage(Message inputMsg , ReplyKeyboardMarkup keyboard ) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(inputMsg.getChatId());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplayToMessageId(inputMsg.getMessageId());
        sendMessage.setReplayMarkup(keyboard);
        return sendMessage;
    }

    public SendMessage handleRestaurant(Message message) {
        return buildMessage(message, getDefaultKeyboard());
    }

    

    public SendMessage handleEvent(Message message) {
        return buildMessage(message, getDefaultKeyboard());
    }


    public SendMessage handlePoll(Message message) {
        return buildMessage(message, getPollKeyBoard());
    }

    public SendMessage handleOrder(Message message) {
        return buildMessage(message, getPollKeyBoard());
    }
}
