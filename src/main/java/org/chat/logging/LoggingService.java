package org.chat.logging;

import io.quarkus.logging.Log;

public class LoggingService {

    public static void info(String info) {
        Log.info(info);
    }

}
