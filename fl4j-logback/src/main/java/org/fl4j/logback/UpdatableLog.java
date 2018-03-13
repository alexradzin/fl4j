package org.fl4j.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import org.fl4j.Log;
import org.fl4j.LogLevel;
import org.fl4j.NoOpLog;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Implementation of {@link Log} that wraps real {@link Log} and updates it when configuration is being updated dynamically.
 * Created by alexander on 2/5/18.
 */
public class UpdatableLog implements Log, LoggerContextListener {
    private final Logger logger;
    private volatile Log log;


    private static final Map<LogLevel, Level> logLevels = new EnumMap<>(LogLevel.class);
    static {
        logLevels.put(LogLevel.NONE, Level.OFF);
        logLevels.put(LogLevel.FATAL, Level.ERROR);
        logLevels.put(LogLevel.ERROR, Level.ERROR);
        logLevels.put(LogLevel.WARNING, Level.WARN);
        logLevels.put(LogLevel.INFO, Level.INFO);
        logLevels.put(LogLevel.DEBUG, Level.DEBUG);
        logLevels.put(LogLevel.TRACE, Level.TRACE);
        logLevels.put(LogLevel.ALL, Level.ALL);
    }
    private static final Map<Level, LogLevel> logLevelsBack =
            logLevels.entrySet().stream().collect(Collectors.toMap(
                    Map.Entry::getValue,
                    Map.Entry::getKey,
                    (v1, v2) -> v2
            ));

    private static final Map<LogLevel, Function<Logger, Log>> logFactories = new EnumMap<>(LogLevel.class);
    static {
        logFactories.put(LogLevel.NONE, logger -> new NoOpLog());
        logFactories.put(LogLevel.INFO, InfoLog::new);
        logFactories.put(LogLevel.DEBUG, DebugLog::new);
        logFactories.put(LogLevel.TRACE, TraceLog::new);
        logFactories.put(LogLevel.WARNING, WarnLog::new);
        logFactories.put(LogLevel.ERROR, ErrorLog::new);
        logFactories.put(LogLevel.FATAL, ErrorLog::new);
    }

    UpdatableLog(LoggerContext loggerContext, String name, LogLevel initialLevel) {
        loggerContext.addListener(this);
        logger = loggerContext.getLogger(name);
        log = createLog(logger.isEnabledFor(logLevels.get(initialLevel)) ? initialLevel: LogLevel.NONE, logger);
    }

    private Log createLog(LogLevel level, Logger logger) {
        return logFactories.get(level).apply(logger);
    }


    public String log(String fmt, Object ... args) {
        return log.log(fmt, args);
    }

    public boolean isEnabled() {
        return log.isEnabled();
    }

    @Override
    public String log(String msg) {
        return log.log(msg);
    }


    @Override
    public <T> T log(String fmt, T arg) {
        return log.log(fmt, arg);
    }

    @Override
    public <T> T log(String fmt, T arg, Function<? super T, ?> toStr) {
        return log.log(fmt, arg, toStr);
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2) {
        return log.log(fmt, arg1, toStr1, arg2);
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2) {
        return log.log(fmt, arg1, arg2);
    }



    @Override
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, T3 arg3) {
        return log.log(fmt, arg1, toStr1, arg2, arg3);
    }

    @Override
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, T2 arg2, T3 arg3) {
        return log.log(fmt, arg1, arg2, arg3);
    }


    @Override
    public <E extends Throwable> void exception(String fmt, E e) {
        log.exception(fmt, e);
    }

    @Override
    public <T, E extends Throwable> T exception(String fmt, T arg, E e) {
        return log.exception(fmt, arg, e);
    }

    @Override
    public <T, E extends Throwable> T exception(String fmt, T arg, Function<? super T, ?> toStr, E e) {
        return log.exception(fmt, arg, toStr, e);
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, E e) {
        return log.exception(fmt, arg1, toStr1, arg2, e);
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, E e) {
        return log.exception(fmt, arg1, arg2, e);
    }

    @Override
    public <T1, E extends Throwable> void exception(String fmt, T1 arg1, Supplier<?> toStr2, E e) {
        log.exception(fmt, arg1, toStr2, e);
    }

    @Override
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, T3 arg3, E e) {
        return log.exception(fmt, arg1, toStr1, arg2, arg3, e);
    }

    @Override
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, T3 arg3, E e) {
        return log.exception(fmt, arg1, arg2, arg3, e);
    }

    @Override
    public void log(String fmt, Supplier<?> toStr1, Supplier<?> toStr2) {
        log.log(fmt, toStr1, toStr2);
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2) {
        return log.log(fmt, arg1, toStr1, toStr2);
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Supplier<?> toStr2) {
        return log.log(fmt, arg1, toStr2);
    }

    @Override
    public void log(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3) {
        log.log(fmt, toStr1, toStr2, toStr3);
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Supplier<?> toStr2, Supplier<?> toStr3) {
        return log.log(fmt, arg1, toStr2, toStr3);
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2, Supplier<?> toStr3) {
        return log.log(fmt, arg1, arg2, toStr3);
    }

    @Override
    public <T1, T3> T1 log(String fmt, T1 arg1, Supplier<?> toStr2, T3 arg3) {
        return log.log(fmt, arg1, toStr2, arg3);
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3) {
        return log.log(fmt, arg1, toStr1, toStr2, toStr3);
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, Supplier<?> toStr3) {
        return log.log(fmt, arg1, toStr1, arg2, toStr3);
    }

    @Override
    public <T1, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, T3 arg3) {
        return log.log(fmt, arg1, toStr1, toStr2, arg3);
    }

    @Override
    public <E extends Throwable> void exception(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, E e) {
        log.log(fmt, toStr1, toStr2, e);
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, E e) {
        return log.exception(fmt, arg1, toStr1, toStr2, e);
    }

    @Override
    public <E extends Throwable> void exception(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        log.exception(fmt, toStr1, toStr2, toStr3, e);
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        return log.exception(fmt, arg1, toStr2, toStr3, e);
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, Supplier<?> toStr3, E e) {
        return log.exception(fmt, arg1, arg2, toStr3, e);
    }

    @Override
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<?> toStr2, T3 arg3, E e) {
        return log.exception(fmt, arg1, toStr2, arg3, e);
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        return log.exception(fmt, arg1, toStr1, toStr2, toStr3, e);
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, Supplier<?> toStr3, E e) {
        return log.exception(fmt, arg1, toStr1, arg2, toStr3, e);
    }

    @Override
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, T3 arg3, E e) {
        return log.exception(fmt, arg1, toStr1, toStr2, arg3, e);
    }

    @Override
    public boolean isResetResistant() {
        return false;
    }

    @Override
    public void onStart(LoggerContext loggerContext) {

    }

    @Override
    public void onReset(LoggerContext loggerContext) {

    }

    @Override
    public void onStop(LoggerContext loggerContext) {
        log = createLog(LogLevel.NONE, logger);
    }

    @Override
    public void onLevelChange(Logger logger, Level level) {
        log = createLog(logger.isEnabledFor(level) ? logLevelsBack.get(level): LogLevel.NONE, logger);
    }
}
