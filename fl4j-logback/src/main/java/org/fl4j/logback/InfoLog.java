package org.fl4j.logback;

import ch.qos.logback.classic.Logger;
import org.fl4j.Log;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.fl4j.Getter.get;

/**
 * Implementation of {@link Log} for INFO Logback
 * Created by alexander on 2/5/18.
 */
class InfoLog implements Log {
    private final Logger logger;

    InfoLog(Logger logger) {
        this.logger = logger;
    }

    @Override
    public String log(String msg) {
        logger.info(msg);
        return msg;
    }

    public String log(String fmt, Object ... args) {
        logger.info(fmt, args);
        return args[0] == null ? null : args[0].toString();
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public <T> T log(String fmt, T arg) {
        logger.info(fmt, arg);
        return arg;
    }

    @Override
    public <T> T log(String fmt, T arg, Function<? super T, ? extends String> toStr) {
        logger.info(fmt, toStr.apply(arg));
        return arg;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2) {
        logger.info(fmt, toStr1.apply(arg1), arg2);
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2) {
        logger.info(fmt, arg1, arg2);
        return arg1;
    }

    @Override
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, T3 arg3) {
        logger.info(fmt, toStr1.apply(arg1), arg2, arg3);
        return arg1;
    }

    @Override
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, T2 arg2, T3 arg3) {
        logger.info(fmt, arg1, arg2, arg3);
        return arg1;
    }

    @Override
    public void log(String fmt, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2) {
        logger.info(fmt, get(toStr1), get(toStr2));
    }

    @Override
    public <T> T log(String fmt, T arg1, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2) {
        logger.info(fmt, get(toStr1), get(toStr2));
        return arg1;
    }

    @Override
    public <E extends Throwable> void exception(String fmt, E e) {
        logger.info(fmt, e);
    }


    @Override
    public <T, E extends Throwable> T exception(String fmt, T arg, E e) {
        logger.info(fmt, arg, e);
        return arg;
    }

    @Override
    public <T, E extends Throwable> T exception(String fmt, T arg, Function<? super T, ? extends String> toStr, E e) {
        logger.info(fmt, toStr.apply(arg), e);
        return arg;
    }

    @Override
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, T3 arg3, E e) {
        logger.info(fmt, toStr1.apply(arg1), arg2, arg3, e);
        return arg1;
    }

    @Override
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, T3 arg3, E e) {
        logger.info(fmt, arg1, arg2, arg3, e);
        return arg1;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2) {
        logger.info(fmt, toStr1.apply(arg1), get(toStr2));
        return arg1;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Supplier<? extends String> toStr2) {
        logger.info(fmt, arg1, get(toStr2));
        return arg1;
    }

    @Override
    public void log(String fmt, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3) {
        logger.info(fmt, get(toStr1), get(toStr2), get(toStr3));
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2, Supplier<? extends String> toStr3) {
        logger.info(fmt, arg1, arg2, get(toStr3));
        return arg1;
    }

    @Override
    public <T1, T3> T1 log(String fmt, T1 arg1, Supplier<? extends String> toStr2, T3 arg3) {
        logger.info(fmt, arg1, get(toStr2), arg3);
        return arg1;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3) {
        logger.info(fmt, toStr1.apply(arg1), get(toStr2), get(toStr3));
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, Supplier<? extends String> toStr3) {
        logger.info(fmt, toStr1.apply(arg1), arg2, get(toStr3));
        return arg1;
    }

    @Override
    public <T1, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, T3 arg3) {
        logger.info(fmt, toStr1.apply(arg1), get(toStr2), arg3);
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, E e) {
        logger.info(fmt, toStr1.apply(arg1), arg2, e);
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, E e) {
        logger.info(fmt, arg1, arg2, e);
        return arg1;
    }

    @Override
    public <T1, E extends Throwable> void exception(String fmt, T1 arg1, Supplier<? extends String> toStr2, E e) {
        logger.info(fmt, arg1, get(toStr2), e);
    }

    @Override
    public <E extends Throwable> void exception(String fmt, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2, E e) {
        logger.info(fmt, get(toStr1), get(toStr2), e);
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, E e) {
        logger.info(fmt, toStr1.apply(arg1), get(toStr2), e);
        return arg1;
    }

    @Override
    public <E extends Throwable> void exception(String fmt, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3, E e) {
        logger.info(fmt, get(toStr1), get(toStr2), get(toStr3), e);
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3, E e) {
        logger.info(fmt, arg1, get(toStr2), get(toStr3), e);
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, Supplier<? extends String> toStr3, E e) {
        logger.info(fmt, arg1, arg2, get(toStr3), e);
        return arg1;
    }

    @Override
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<? extends String> toStr2, T3 arg3, E e) {
        logger.info(fmt, arg1, get(toStr2), arg3, e);
        return arg1;
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3, E e) {
        logger.info(fmt, toStr1.apply(arg1), get(toStr2), get(toStr3), e);
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, Supplier<? extends String> toStr3, E e) {
        logger.info(fmt, toStr1.apply(arg1), arg2, get(toStr3), e);
        return arg1;
    }

    @Override
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, T3 arg3, E e) {
        logger.info(fmt, toStr1.apply(arg1), get(toStr2), arg3, e);
        return arg1;
    }
}
