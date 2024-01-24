package final_sc;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    final static Logger LOGGER = Logger.getLogger("MyLog");

    static {
        try {
            FileHandler fh = new FileHandler("logfile.log", true);
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.setUseParentHandlers(false);

        } catch (IOException | SecurityException e) {
            System.out.println("Logger has a problem: " + e.getMessage());
        }
    }

    public static void writeToLog(String msg) {
        LOGGER.log(Level.INFO, msg);
    }

    public static void writeToLog(String msg, Exception e) {
        LOGGER.log(Level.WARNING, msg, e);
    }
}
