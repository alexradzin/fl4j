package org.fl4j.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.fl4j.LoggerAdapterBase;


/**
 * Implementation of {@link org.fl4j.Log} for LOG4J
 * Created by alexander on 2/20/18.
 */
class Log4jAdapter extends LoggerAdapterBase {
    private final static String SELF = Log4jAdapter.class.getName();
    private final Logger logger;
    private final Priority priority;

    Log4jAdapter(Logger logger) {
        this.logger = logger;
        this.priority = logger.getLevel();
    }

    @Override
    protected <E extends Throwable> void write(CharSequence msg, E e) {
        logger.log(SELF, priority, msg, e);
    }
}
