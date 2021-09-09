package sda.cars.carrental.printout.profile;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * change log level at runtime
 * Created by nkcoder on 10/8/16.
 */

public class ChangeLogLevel {

    private static final Logger logger = LoggerFactory.getLogger(ChangeLogLevel.class);
    private static String cachedLevel = "";

    public ChangeLogLevel toLevel(final String logLevel) {

        Level level = Level.toLevel(logLevel.toUpperCase());
        if (cachedLevel.equalsIgnoreCase(level.levelStr)) {
            logger.debug("level: {} not changed", cachedLevel);
            return this;
        }

        logger.info("level will change from: {} to: {}", cachedLevel, level.levelStr);
        cachedLevel = level.levelStr;

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<ch.qos.logback.classic.Logger> loggerList = loggerContext.getLoggerList();
        loggerList.stream().forEach(tmpLogger -> tmpLogger.setLevel(level));

        return this;
    }
}