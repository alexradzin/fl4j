package org.fl4j.juli;

import org.fl4j.*;

import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by alexander on 2/12/18.
 */
public class JuliLogProvider implements LogProvider {
    private static final Map<LogLevel, Level> logLevelMapping = new EnumMap<>(LogLevel.class);
    static {
        logLevelMapping.put(LogLevel.NONE, Level.OFF);
        logLevelMapping.put(LogLevel.INFO, Level.INFO);
        logLevelMapping.put(LogLevel.DEBUG, Level.FINE);
        logLevelMapping.put(LogLevel.TRACE, Level.FINER);
        logLevelMapping.put(LogLevel.WARINIG, Level.WARNING);
        logLevelMapping.put(LogLevel.ERROR, Level.SEVERE);
        logLevelMapping.put(LogLevel.FATAL, Level.SEVERE);
    }


    @Override
    public Log create(LogBuilder builder) {
        Logger logger = Logger.getLogger(builder.getLogName());
        logger.setLevel(logLevelMapping.getOrDefault(builder.getLogLevel(), Level.INFO));
        return new JuliLoggerAdapter(Logger.getLogger(builder.getLogName()));
    }

    /**
     * JULI is a part of JDK, so it is always available.
     * TODO: check what happens in JDK 9
     * @return true
     */
    @Override
    public boolean isAvailable() {
        return true;
    }
}
