package org.fl4j.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.fl4j.*;

import java.util.EnumMap;
import java.util.Map;

/**
 * Implementation of {@link LogProvider} for LOG4J
 * Created by alexander on 2/20/18.
 */
public class Log4jProvider extends ClassAvailabilityCheckingLogProvider {
    private static final Map<LogLevel, Level> logLevelMapping = new EnumMap<>(LogLevel.class);
    static {
        logLevelMapping.put(LogLevel.NONE, Level.OFF);
        logLevelMapping.put(LogLevel.INFO, Level.INFO);
        logLevelMapping.put(LogLevel.DEBUG, Level.DEBUG);
        logLevelMapping.put(LogLevel.TRACE, Level.TRACE);
        logLevelMapping.put(LogLevel.WARNING, Level.WARN);
        logLevelMapping.put(LogLevel.ERROR, Level.ERROR);
        logLevelMapping.put(LogLevel.FATAL, Level.FATAL);
    }

    public Log4jProvider() {
        super("org.apache.log4j.Category");
    }

    @Override
    public Log create(LogBuilder builder) {
        Logger logger = Logger.getLogger(builder.getLogName());
        logger.setLevel(logLevelMapping.getOrDefault(builder.getLogLevel(), Level.INFO));
        return new Log4jAdapter(logger);
    }

    @Override
    public int getPriority() {
        return 30;
    }

}
