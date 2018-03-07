package org.fl4j.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import org.fl4j.ClassAvailabilityCheckingLogProvider;
import org.fl4j.Log;
import org.fl4j.LogBuilder;

/**
 * Created by alexander on 2/5/18.
 */
public class LogBackProvider extends ClassAvailabilityCheckingLogProvider {
    private static final String packageName = LogBackProvider.class.getPackage().getName();
    private final LoggerContext defaultLoggerContext;

    public LogBackProvider() {
        super("ch.qos.logback.classic.LoggerContext");
        // Pure standalone Logback only configuration
        defaultLoggerContext = new LoggerContext();
        defaultLoggerContext.getFrameworkPackages().add(packageName);
        try {
            new ContextInitializer(defaultLoggerContext).autoConfig();
        } catch (JoranException e) {
            throw new  IllegalStateException(e);
        }
    }

    @Override
    public Log create(LogBuilder builder) {
        return new UpdatableLog(defaultLoggerContext, builder.getLogName(), builder.getLogLevel());
    }

    @Override
    public int getPriority() {
        return 10;
    }
}
