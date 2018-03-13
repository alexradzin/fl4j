package org.fl4j;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Empty implementation of {@link Log} that is used for disabled logs (logs with level higher than enabled in current environment)
 * Created by alexander on 2/4/18.
 */
public class NoOpLog implements Log {
    public String log(String fmt, Object ... args) {
        return args[0] == null ? null : args[0].toString();
    }

    public boolean isEnabled() {
        return false;
    }

    @Override
    public String log(String msg) {
        return msg;
    }

    @Override
    public <T> T log(String fmt, T arg) {
        return arg;
    }

    @Override
    public <T> T log(String fmt, T arg, Function<? super T, ?> toStr) {
        return arg;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2) {
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2) {
        return arg1;
    }

    @Override
    public void log(String fmt, Supplier<?> toStr1, Supplier<?> toStr2) {

    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2) {
        return arg1;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Supplier<?> toStr2) {
        return arg1;
    }

    @Override
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, T3 arg3) {
        return arg1;
    }

    @Override
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, T2 arg2, T3 arg3) {
        return arg1;
    }

    @Override
    public void log(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3) {

    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Supplier<?> toStr2, Supplier<?> toStr3) {
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2, Supplier<?> toStr3) {
        return arg1;
    }

    @Override
    public <T1, T3> T1 log(String fmt, T1 arg1, Supplier<?> toStr2, T3 arg3) {
        return arg1;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3) {
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, Supplier<?> toStr3) {
        return arg1;
    }

    @Override
    public <T1, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, T3 arg3) {
        return arg1;
    }

    @Override
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, T3 arg3, E e) {
        return arg1;
    }

    @Override
    public <E extends Throwable> void exception(String fmt, E e) {

    }

    @Override
    public <T, E extends Throwable> T exception(String fmt, T arg, E e) {
        return arg;
    }

    @Override
    public <T, E extends Throwable> T exception(String fmt, T arg, Function<? super T, ?> toStr, E e) {
        return arg;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, E e) {
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, E e) {
        return arg1;
    }

    @Override
    public <T1, E extends Throwable> void exception(String fmt, T1 arg1, Supplier<?> toStr2, E e) {

    }

    @Override
    public <E extends Throwable> void exception(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, E e) {

    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, E e) {
        return arg1;
    }

    @Override
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, T3 arg3, E e) {
        return arg1;
    }

    @Override
    public <E extends Throwable> void exception(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {

    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, Supplier<?> toStr3, E e) {
        return arg1;
    }

    @Override
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<?> toStr2, T3 arg3, E e) {
        return arg1;
    }

    @Override
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        return arg1;
    }

    @Override
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, Supplier<?> toStr3, E e) {
        return arg1;
    }

    @Override
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, T3 arg3, E e) {
        return arg1;
    }

}
