package org.fl4j.log4j;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.fl4j.Log;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.fl4j.Applier.apply;
import static org.fl4j.Getter.get;

/**
 * Implementation of {@link Log} for LOG4J2
 * Created by alexander on 2/21/18.
 */
class Log4j2Adapter implements Log { //extends LoggerAdapterBase {
    private final static String SELF = Log4j2Adapter.class.getName();
    private final ExtendedLogger logger;
    private final Level level;

    Log4j2Adapter(Logger logger) {
        this.logger = (ExtendedLogger)logger; // TODO: is this casting always legal?
        this.level = logger.getLevel();
    }


    @Override
    public String log(String fmt, Object... args) {
        logger.logIfEnabled(SELF, level, null, fmt, args);
        return args.length > 0 && args[0] != null ? args[0].toString() : null;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String log(String msg) {
        logger.logIfEnabled(SELF, level, null, msg);
        return msg;
    }

    @Override
    public <T> T log(String fmt, T arg) {
        logger.logIfEnabled(SELF, level, null, fmt, arg);
        return arg;
    }

    @Override
    public <T> T log(String fmt, T arg, Function<? super T, ?> toStr) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr, arg));
        return arg;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr1, arg1), arg2);
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2) {
        logger.logIfEnabled(SELF, level, null, fmt, arg1, arg2);
        return arg1;
    }

    @Override
    public void log(String fmt, Supplier<?> toStr1, Supplier<?> toStr2) {
        logger.logIfEnabled(SELF, level, null, fmt, get(toStr1), get(toStr2));
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr1, arg1), get(toStr2));
        return arg1;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Supplier<?> toStr2) {
        logger.logIfEnabled(SELF, level, null, fmt, arg1, get(toStr2));
        return arg1;
    }

    @Override
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, T3 arg3) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr1, arg1), arg2, arg3);
        return arg1;
    }

    @Override
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, T2 arg2, T3 arg3) {
        logger.logIfEnabled(SELF, level, null, fmt, arg1, arg2, arg3);
        return arg1;
    }

    @Override
    public void log(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3) {
        logger.logIfEnabled(SELF, level, null, fmt, get(toStr1), get(toStr2), get(toStr3));
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Supplier<?> toStr2, Supplier<?> toStr3) {
        logger.logIfEnabled(SELF, level, null, fmt, arg1, get(toStr2), get(toStr3));
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2, Supplier<?> toStr3) {
        logger.logIfEnabled(SELF, level, null, fmt, arg1, arg2, get(toStr3));
        return arg1;
    }

    @Override
    public <T1, T3> T1 log(String fmt, T1 arg1, Supplier<?> toStr2, T3 arg3) {
        logger.logIfEnabled(SELF, level, null, fmt, arg1, get(toStr2), arg3);
        return arg1;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr1, arg1), get(toStr2), get(toStr3));
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, Supplier<?> toStr3) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr1, arg1), arg2, get(toStr3));
        return arg1;
    }

    @Override
    public <T1, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, T3 arg3) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr1, arg1), get(toStr2), arg3);
        return arg1;
    }

    @Override
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, T3 arg3, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, arg1, arg2, arg3, e);
        return arg1;
    }

    @Override
    public <E extends Throwable> void exception(String fmt, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, e);
    }

    @Override
    public <T, E extends Throwable> T exception(String fmt, T arg, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, arg, e);
        return arg;
    }

    @Override
    public <T, E extends Throwable> T exception(String fmt, T arg, Function<? super T, ?> toStr, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr, arg), e);
        return arg;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr1, arg1), arg2, e);
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, arg1, arg2, e);
        return arg1;
    }

    @Override
    public <T1, E extends Throwable> void exception(String fmt, T1 arg1, Supplier<?> toStr2, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, get(toStr2), e);
    }

    @Override
    public <E extends Throwable> void exception(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, get(toStr1), get(toStr2), e);
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr1, arg1), get(toStr2), e);
        return arg1;
    }

    @Override
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, T3 arg3, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr1, arg1), arg2, arg3, e);
        return arg1;
    }

    @Override
    public <E extends Throwable> void exception(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, get(toStr1), get(toStr2), get(toStr3), e);
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, arg1, get(toStr2), get(toStr3), e);
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, Supplier<?> toStr3, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, arg1, arg2, get(toStr3), e);
        return arg1;
    }

    @Override
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<?> toStr2, T3 arg3, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, arg1, get(toStr2), arg3, e);
        return arg1;
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr1, arg1), get(toStr2), get(toStr3), e);
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, Supplier<?> toStr3, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr1, arg1), arg2, get(toStr3), e);
        return arg1;
    }

    @Override
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, T3 arg3, E e) {
        logger.logIfEnabled(SELF, level, null, fmt, apply(toStr1, arg1), get(toStr2), arg3, e);
        return arg1;
    }
}
