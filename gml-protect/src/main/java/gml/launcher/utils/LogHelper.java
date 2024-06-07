package gml.launcher.utils;

import java.util.logging.Logger;

public class LogHelper {
    private static final Logger logger = Logger.getLogger(LogHelper.class.getName());
    private static final String INIT_MESSAGE = "[Gml.Protect] Initialize";
    private static final String SERVER_MESSAGE = "[Gml.Protect] Server: ";
    private static final String TOKEN_MESSAGE = "[Gml.Protect] Token: ";
    public static void logInitMessage() {
        logger.info(INIT_MESSAGE);
    }
    public static void logServer(String url) {
        logger.info(SERVER_MESSAGE + url);
    }
    public static void logToken(String token) {
        logger.info(TOKEN_MESSAGE + token);
    }
}