package org.fl4j.log4j;

import org.apache.logging.log4j.LogManager;
import org.fl4j.ClassAvailabilityCheckingLogProvider;
import org.fl4j.Log;
import org.fl4j.LogBuilder;

/**
 * Created by alexander on 2/21/18.
 */
public class Log4j2Provider extends ClassAvailabilityCheckingLogProvider {
    public Log4j2Provider() {
        super("org.apache.logging.log4j.LogManagerLogManager");
    }

    @Override
    public Log create(LogBuilder builder) {
        return new Log4j2Adapter(LogManager.getLogger(builder.getLogName()));
    }

    @Override
    public int getPriority() {
        return 20;
    }
}
