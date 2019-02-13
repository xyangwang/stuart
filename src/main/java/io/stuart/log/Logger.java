package io.stuart.log;

public class Logger {

    private static volatile org.slf4j.Logger logger;

    public static org.slf4j.Logger log() {
        if (logger == null) {
            synchronized (Logger.class) {
                if (logger == null) {
                    logger = org.slf4j.LoggerFactory.getLogger("");
                }
            }
        }

        return logger;
    }

}
