package org.fl4j;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.fl4j.Applier.apply;
import static org.fl4j.Getter.get;

/**
 * Base class for adaptor of {@link Log} for any other logger that does not support log formats.
 * Created by alexander on 2/12/18.
 */
public abstract class LoggerAdapterBase implements Log {
    private static final LogFormatParser parser = new LogFormatParser();
    private static final MessageBuilder messageBuilder = new MessageBuilder();

    protected LoggerAdapterBase() {
        // empty implementation; defined only to make the constructor protected
    }


    @Override
    public <T> T all(String fmt, Object... args) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);

        Throwable t = null;
        int last = args.length;
        if (args.length > 0 && args[args.length - 1] instanceof Throwable) {
            t = (Throwable) args[args.length - 1];
            last = args.length - 1;
        }

        Object result = last > 1 ? args[0] : null;
        int index = 0;
        int i = 0;
        if (last > 2 && args[1] instanceof Function) {
            @SuppressWarnings("unchecked")
            Object value = ((Function<Object, String>)args[1]).apply(args[0]);
            index = messageBuilder.append(buf, template, index, value);
            i = 2;
        }

        for (; i < last && index >= 0; i++) {
            Object value = args[i];
            if (value instanceof Supplier) {
                //noinspection unchecked
                value = ((Supplier<Object>)value).get();
            }
            index = messageBuilder.append(buf, template, index, value);
        }
        write(index == 0 ? fmt : buf, t);
        @SuppressWarnings("unchecked")
        T typedResult =  (T)result;
        return typedResult;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String simple(String msg) {
        write(msg, null);
        return msg;
    }

    @Override
    public <T> T log(String fmt, T arg) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        messageBuilder.append(buf, template, 0, arg);
        write(buf, null);
        return arg;
    }

    @Override
    public <T> T log(String fmt, T arg, Function<? super T, ?> toStr) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        messageBuilder.append(buf, template, 0, apply(toStr, arg));
        write(buf, null);
        return arg;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        messageBuilder.append(buf, template, i, get(toStr2));
        write(buf, null);
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        messageBuilder.append(buf, template, i, arg2);
        write(buf, null);
        return arg1;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Supplier<?> toStr2) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        messageBuilder.append(buf, template, i, get(toStr2));
        write(buf, null);
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        messageBuilder.append(buf, template, i, arg2);
        write(buf, null);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        i = messageBuilder.append(buf, template, i, get(toStr2));
        i = messageBuilder.append(buf, template, i, get(toStr3));
        write(buf, null);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1> T1 log(String fmt, T1 arg1, Supplier<?> toStr2, Supplier<?> toStr3) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        i = messageBuilder.append(buf, template, i, get(toStr2));
        i = messageBuilder.append(buf, template, i, get(toStr3));
        write(buf, null);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, Supplier<?> toStr3) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        i = messageBuilder.append(buf, template, i, arg2);
        i = messageBuilder.append(buf, template, i, get(toStr3));
        write(buf, null);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, T3 arg3) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        i = messageBuilder.append(buf, template, i, get(toStr2));
        i = messageBuilder.append(buf, template, i, arg3);
        write(buf, null);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2, Supplier<?> toStr3) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        i = messageBuilder.append(buf, template, i, arg2);
        i = messageBuilder.append(buf, template, i, get(toStr3));
        write(buf, null);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T3> T1 log(String fmt, T1 arg1, Supplier<?> toStr2, T3 arg3) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        i = messageBuilder.append(buf, template, i, get(toStr2));
        i = messageBuilder.append(buf, template, i, arg3);
        write(buf, null);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, T3 arg3) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        i = messageBuilder.append(buf, template, i, arg2);
        i = messageBuilder.append(buf, template, i, arg3);
        write(buf, null);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, T2 arg2, T3 arg3) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        i = messageBuilder.append(buf, template, i, arg2);
        i = messageBuilder.append(buf, template, i, arg3);
        write(buf, null);
        return arg1;
    }

    @Override
    public <E extends Throwable> void exception(String fmt, E e) {
        write(fmt, e);
    }

    @Override
    public <T, E extends Throwable> T exception(String fmt, T arg, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        messageBuilder.append(buf, template, 0, arg);
        write(buf, e);
        return arg;
    }

    @Override
    public <T, E extends Throwable> T exception(String fmt, T arg, Function<? super T, ?> toStr, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        messageBuilder.append(buf, template, 0, apply(toStr, arg));
        write(buf, e);
        return arg;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        i = messageBuilder.append(buf, template, i, get(toStr2));
        write(buf, e);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        i = messageBuilder.append(buf, template, i, arg2);
        write(buf, e);
        return arg1;
    }


    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        i = messageBuilder.append(buf, template, i, arg2);
        write(buf, e);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        i = messageBuilder.append(buf, template, i, get(toStr2));
        i = messageBuilder.append(buf, template, i, get(toStr3));
        write(buf, e);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        i = messageBuilder.append(buf, template, i, get(toStr2));
        i = messageBuilder.append(buf, template, i, get(toStr3));
        write(buf, e);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, Supplier<?> toStr3, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        i = messageBuilder.append(buf, template, i, arg2);
        i = messageBuilder.append(buf, template, i, get(toStr3));
        write(buf, e);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, Supplier<?> toStr2, T3 arg3, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        i = messageBuilder.append(buf, template, i, get(toStr2));
        i = messageBuilder.append(buf, template, i, arg3);
        write(buf, e);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, Supplier<?> toStr3, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        i = messageBuilder.append(buf, template, i, arg2);
        i = messageBuilder.append(buf, template, i, get(toStr3));
        write(buf, e);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<?> toStr2, T3 arg3, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        i = messageBuilder.append(buf, template, i, get(toStr2));
        i = messageBuilder.append(buf, template, i, arg3);
        write(buf, e);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ?> toStr1, T2 arg2, T3 arg3, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        i = messageBuilder.append(buf, template, i, arg2);
        i = messageBuilder.append(buf, template, i, arg3);
        write(buf, e);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, T3 arg3, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        i = messageBuilder.append(buf, template, i, arg2);
        i = messageBuilder.append(buf, template, i, arg3);
        write(buf, e);
        return arg1;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public void log(String fmt, Supplier<?> toStr1, Supplier<?> toStr2) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, get(toStr1));
        i = messageBuilder.append(buf, template, i, get(toStr2));
        write(buf, null);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public void log(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, get(toStr1));
        i = messageBuilder.append(buf, template, i, get(toStr2));
        i = messageBuilder.append(buf, template, i, get(toStr3));
        write(buf, null);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, E extends Throwable> void exception(String fmt, T1 arg1, Supplier<?> toStr2, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        i = messageBuilder.append(buf, template, i, get(toStr2));
        write(buf, e);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <E extends Throwable> void exception(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, get(toStr1));
        i = messageBuilder.append(buf, template, i, get(toStr2));
        write(buf, e);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <E extends Throwable> void exception(String fmt, Supplier<?> toStr1, Supplier<?> toStr2, Supplier<?> toStr3, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, get(toStr1));
        i = messageBuilder.append(buf, template, i, get(toStr2));
        i = messageBuilder.append(buf, template, i, get(toStr3));
        write(buf, e);
    }

    protected abstract <E extends Throwable> void write(CharSequence record, E e);
}


