package org.fl4j;

import java.util.function.Function;

/**
 * Created by alexander on 2/4/18.
 */
public class StringFormatLogFormatter implements LogFormatter {
    @Override
    public <T> String format(String fmt, T arg) {
        return String.format(fmt, arg);
    }

    @Override
    public <T> String format(String fmt, T arg, Function<? super T, ? extends String> toStr) {
        return String.format(fmt, toStr.apply(arg));
    }

    @Override
    public <T1, T2> String format(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, Function<? super T2, ? extends String> toStr2) {
        return null;
    }

    @Override
    public <T1, T2> String format(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2) {
        return null;
    }

    @Override
    public <T1, T2> String format(String fmt, T1 arg1, T2 arg2, Function<T2, ? extends String> toStr2) {
        return null;
    }

    @Override
    public <T1, T2> String format(String fmt, T1 arg1, T2 arg2) {
        return null;
    }
}
