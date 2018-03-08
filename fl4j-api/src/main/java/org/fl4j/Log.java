package org.fl4j;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * General log interface. Defines all methods that can be used for logging.
 * Created by alexander on 2/1/18.
 * @author alexr
 */
public interface Log {
    String log(String fmt, Object ... args);
    boolean isEnabled();
    String log(String msg);
    <T> T log(String fmt, T arg);
    <T> T log(String fmt, T arg, Function<? super T, ? extends String> toStr);

    <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2);
    <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2);


    void log(String fmt, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2);
    <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2);
    <T1> T1 log(String fmt, T1 arg1, Supplier<? extends String> toStr2);


    <T1, T2, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, T3 arg3);
    <T1, T2, T3> T1 log(String fmt, T1 arg1, T2 arg2, T3 arg3);


    void log(String fmt, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3);
    <T1> T1 log(String fmt, T1 arg1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3);
    <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2, Supplier<? extends String> toStr3);
    <T1, T3> T1 log(String fmt, T1 arg1, Supplier<? extends String> toStr2, T3 arg3);

    <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3);
    <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, Supplier<? extends String> toStr3);
    <T1, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, T3 arg3);

    <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, T3 arg3, E e);
    <E extends Throwable> void exception(String fmt, E e);
    <T, E extends Throwable> T exception(String fmt, T arg, E e);
    <T, E extends Throwable> T exception(String fmt, T arg, Function<? super T, ? extends String> toStr, E e);
    <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, E e);
    <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, E e);
    <T1, E extends Throwable> void exception(String fmt, T1 arg1, Supplier<? extends String> toStr2, E e);
    <E extends Throwable> void exception(String fmt, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2, E e);
    <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, E e);
    <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, T3 arg3, E e);
    //<T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, T3 arg3, E e);
    <E extends Throwable> void exception(String fmt, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3, E e);
    <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3, E e);
    <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, Supplier<? extends String> toStr3, E e);
    <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<? extends String> toStr2, T3 arg3, E e);
    <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3, E e);
    <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, Supplier<? extends String> toStr3, E e);
    <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, T3 arg3, E e);
}
