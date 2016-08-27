package org.telegram;

import org.telegram.updateshandlers.UpdatesCallback;

public class Main {

    public static void main(String[] args) {

        initBots();
    }

    private static void initBots() {
      UpdatesCallback holaBot = new HolaHandlers();

      
//      UpdatesCallback holaBot = new JenkinsHandlers();
    }
}
