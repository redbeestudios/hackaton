package io.redbee;

import org.telegram.updateshandlers.LunchHandler;
import org.telegram.updateshandlers.UpdatesCallback;

public class Main {

    public static void main(String[] args) {
        UpdatesCallback lunchHandler = new LunchHandler();
    }

}
