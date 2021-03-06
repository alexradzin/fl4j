package org.fl4j.logback;

import ch.qos.logback.classic.Logger;
import org.fl4j.Log;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.fl4j.Getter.get;

/**
 * Implementation of {@link Log} for WARN Logback
 * Created by alexander on 2/5/18.
 */
class WarnLog extends VarArgsSupport implements Log {
    private final Logger logger;

    WarnLog(Logger logger) {
        this.logger = logger;
    }

    @Override
    public String simple(String msg) {
        logger.warn(msg);
        return msg;
    }

    @Override
    public <T> T all(String fmt, Object ... args) {
        return all(logger, fmt, args);
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public <T> T log(String fmt, T arg) {
        logger.warn(fmt, arg);
        return arg;
    }

    @Override
    public <T> T log(String fmt, T arg, Function<? super T, ?> toStr) {
        logger.warn(fmt, toStr.apply(arg));
        return arg;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2) {
        logger.warn(fmt, toStr1.apply(arg1), arg2);
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2) {
        logger.warn(fmt, arg1, arg2);
        return arg1;
    }

    @Override
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, T3 arg3) {
        logger.warn(fmt, toStr1.apply(arg1), arg2, arg3);
        return arg1;
    }

    @Override
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, T2 arg2, T3 arg3) {
        logger.warn(fmt, arg1, arg2, arg3);
        return arg1;
    }

    @Override
    public void log(String fmt, Supplier<?> toStr1, Supplier<?> toStr2) {
        logger.warn(fmt, get(toStr1), get(toStr2));
    }

    @Override
    public <T> T log(String fmt, T arg1, Supplier<?> toStr1, Supplier<?> toStr2) {
        logger.warn(fmt, get(toStr1), get(toStr2));
        return arg1;
    }

    @Override
    public <E extends Throwable> void exception(String fmt, E e) {
        logger.warn(fmt, e);
    }


    @Override
    public <T, E extends Throwable> T exception(String fmt, T arg, E e) {
        logger.warn(fmt, arg, e);
        return arg;
    }

    @Override
    public <T, E extends Throwable> T exception(String fmt, T arg, Function<? super T, ?> toStr, E e) {
        logger.warn(fmt, toStr.apply(arg), e);
        return arg;
    }

    @Override
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, T3 arg3, E e) {
        logger.warn(fmt, toStr1.apply(arg1), arg2, arg3, e);
        return arg1;
    }

    @Override
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, T3 arg3, E e) {
        logger.warn(fmt, arg1, arg2, arg3, e);
        return arg1;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2) {
        logger.warn(fmt, toStr1.apply(arg1), get(toStr2));
        return arg1;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Supplier<?> toStr2) {
        logger.warn(fmt, arg1, get(toStr2));
        return arg1;
    }

    @Override
    public void log(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3) {
        logger.warn(fmt, get(toStr1), get(toStr2), get(toStr3));
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2, Supplier<?> toStr3) {
        logger.warn(fmt, arg1, arg2, get(toStr3));
        return arg1;
    }

    @Override
    public <T1, T3> T1 log(String fmt, T1 arg1, Supplier<?> toStr2, T3 arg3) {
        logger.warn(fmt, arg1, get(toStr2), arg3);
        return arg1;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3) {
        logger.warn(fmt, toStr1.apply(arg1), get(toStr2), get(toStr3));
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, Supplier<?> toStr3) {
        logger.warn(fmt, toStr1.apply(arg1), arg2, get(toStr3));
        return arg1;
    }

    @Override
    public <T1, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, T3 arg3) {
        logger.warn(fmt, toStr1.apply(arg1), get(toStr2), arg3);
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, E e) {
        logger.warn(fmt, toStr1.apply(arg1), arg2, e);
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, E e) {
        logger.warn(fmt, arg1, arg2, e);
        return arg1;
    }

    @Override
    public <T1, E extends Throwable> void exception(String fmt, T1 arg1, Supplier<?> toStr2, E e) {
        logger.warn(fmt, arg1, get(toStr2), e);
    }

    @Override
    public <E extends Throwable> void exception(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, E e) {
        logger.warn(fmt, get(toStr1), get(toStr2), e);
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, E e) {
        logger.warn(fmt, toStr1.apply(arg1), get(toStr2), e);
        return arg1;
    }

    @Override
    public <E extends Throwable> void exception(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        logger.warn(fmt, get(toStr1), get(toStr2), get(toStr3), e);
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        logger.warn(fmt, arg1, get(toStr2), get(toStr3), e);
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, Supplier<?> toStr3, E e) {
        logger.warn(fmt, arg1, arg2, get(toStr3), e);
        return arg1;
    }

    @Override
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<?> toStr2, T3 arg3, E e) {
        logger.warn(fmt, arg1, get(toStr2), arg3, e);
        return arg1;
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        logger.warn(fmt, toStr1.apply(arg1), get(toStr2), get(toStr3), e);
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, Supplier<?> toStr3, E e) {
        logger.warn(fmt, toStr1.apply(arg1), arg2, get(toStr3), e);
        return arg1;
    }

    @Override
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, T3 arg3, E e) {
        logger.warn(fmt, toStr1.apply(arg1), get(toStr2), arg3, e);
        return arg1;
    }
}
