package org.fl4j.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.fl4j.Log;
import org.fl4j.LoggerAdapterBase;

import java.util.function.Function;
/**
 * Created by alexander on 2/20/18.
 */
public class Log4jAdapter extends LoggerAdapterBase {
    private final static String SELF = Log4jAdapter.class.getName();
    private final Logger logger;
    private final Priority priority;

    public Log4jAdapter(Logger logger) {
        this.logger = logger;
        this.priority = logger.getLevel();
    }

    @Override
    protected <E extends Throwable> void write(CharSequence msg, E e) {
        logger.log(SELF, priority, msg, e);
    }
}
