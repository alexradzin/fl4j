package org.fl4j.juli;

import org.fl4j.Log;
import org.fl4j.LoggerAdapterBase;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Adaptor of {@link Log} for JULI {@link Logger}
 * Created by alexander on 2/12/18.
 */
class JuliLoggerAdapter extends LoggerAdapterBase {
    private final static String SELF = JuliLoggerAdapter.class.getName();
    private final static String SUPER = LoggerAdapterBase.class.getName();
    private final Logger logger;
    private final Level level;


    JuliLoggerAdapter(Logger logger) {
        this.logger = logger;
        level = logger.getLevel();
    }


    @Override
    protected <E extends Throwable> void write(CharSequence s, E e) {
        logger.log(createRecord(s, e));
    }

    private LogRecord createRecord(CharSequence msg, Throwable t) {
        LogRecord record = new LogRecord(level, msg == null ? null : msg.toString());
        record.setLoggerName(logger.getName());
        record.setThrown(t);
        return fillCallerData(record);
    }


    private LogRecord fillCallerData(LogRecord record) {
        StackTraceElement[] steArray = new Throwable().getStackTrace();
        for (StackTraceElement ste : steArray) {
            final String className = ste.getClassName();
            if (!(SELF.equals(className) || SUPER.equals(className))) {
                record.setSourceClassName(ste.getClassName());
                record.setSourceMethodName(ste.getMethodName());
                break;
            }
        }
        return record;
    }
}


