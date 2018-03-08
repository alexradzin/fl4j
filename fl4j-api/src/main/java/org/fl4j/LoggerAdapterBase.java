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
    private static final Object[] EMPTY = new Object[0];

    protected LoggerAdapterBase() {
        // empty implementation; defined only to make the constructor protected
    }


    @Override
    public String log(String fmt, Object... arguments) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);

        Object[] args = arguments;
        Throwable t = null;
        if (arguments.length > 0 && arguments[arguments.length - 1] instanceof Throwable) {
            t = (Throwable)arguments[arguments.length - 1];
            if (arguments.length == 1) {
                args = EMPTY;
            } else {
                args = new Object[arguments.length - 1];
                System.arraycopy(arguments, 0, args, 0, arguments.length - 1);
            }
        }
        Object result = null;

        for (int i = 0, index = 0; i < args.length && index >= 0; i++) {
            Object arg = args[i];
            Object value = arg;
            boolean useForResult = i == 0;
            if (i < args.length - 1) {
                Object next = args[i + 1];
                if (next instanceof Function) {
                    //noinspection unchecked
                    value = ((Function<Object, String>)next).apply(arg);
                    i++;
                }
            }
            if (useForResult) {
                result = value;
            }
            index = messageBuilder.append(buf, template, index, value);
        }
        write(buf, t);
        return result == null ? null : result instanceof String ? (String)result : result.toString();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String log(String msg) {
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
    public <T> T log(String fmt, T arg, Function<? super T, ? extends String> toStr) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        messageBuilder.append(buf, template, 0, apply(toStr, arg));
        write(buf, null);
        return arg;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        messageBuilder.append(buf, template, i, get(toStr2));
        write(buf, null);
        return arg1;
    }

    @Override
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, apply(toStr1, arg1));
        messageBuilder.append(buf, template, i, arg2);
        write(buf, null);
        return arg1;
    }

    @Override
    public <T1> T1 log(String fmt, T1 arg1, Supplier<? extends String> toStr2) {
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
    public <T1> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3) {
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
    public <T1> T1 log(String fmt, T1 arg1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3) {
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
    public <T1, T2> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, Supplier<? extends String> toStr3) {
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
    public <T1, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, T3 arg3) {
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
    public <T1, T2> T1 log(String fmt, T1 arg1, T2 arg2, Supplier<? extends String> toStr3) {
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
    public <T1, T3> T1 log(String fmt, T1 arg1, Supplier<? extends String> toStr2, T3 arg3) {
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
    public <T1, T2, T3> T1 log(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, T3 arg3) {
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
    public <T, E extends Throwable> T exception(String fmt, T arg, Function<? super T, ? extends String> toStr, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        messageBuilder.append(buf, template, 0, apply(toStr, arg));
        write(buf, e);
        return arg;
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, E e) {
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
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, E e) {
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
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3, E e) {
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
    public <T1, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3, E e) {
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
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, Supplier<? extends String> toStr3, E e) {
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
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, Supplier<? extends String> toStr2, T3 arg3, E e) {
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
    public <T1, T2, E extends Throwable> T1 exception(String fmt, T1 arg1, T2 arg2, Supplier<? extends String> toStr3, E e) {
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
    public <T1, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Supplier<? extends String> toStr2, T3 arg3, E e) {
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
    public <T1, T2, T3, E extends Throwable> T1 exception(String fmt, T1 arg1, Function<? super T1, ? extends String> toStr1, T2 arg2, T3 arg3, E e) {
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
    public void log(String fmt, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, get(toStr1));
        i = messageBuilder.append(buf, template, i, get(toStr2));
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public void log(String fmt, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, get(toStr1));
        i = messageBuilder.append(buf, template, i, get(toStr2));
        i = messageBuilder.append(buf, template, i, get(toStr3));
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <T1, E extends Throwable> void exception(String fmt, T1 arg1, Supplier<? extends String> toStr2, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, arg1);
        i = messageBuilder.append(buf, template, i, get(toStr2));
        write(buf, e);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <E extends Throwable> void exception(String fmt, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2, E e) {
        StringBuilder buf = new StringBuilder();
        Object[] template = parser.parse(fmt);
        int i = 0;
        i = messageBuilder.append(buf, template, i, get(toStr1));
        i = messageBuilder.append(buf, template, i, get(toStr2));
        write(buf, e);
    }

    @Override
    @SuppressWarnings("UnusedAssignment")
    public <E extends Throwable> void exception(String fmt, Supplier<? extends String> toStr1, Supplier<? extends String> toStr2, Supplier<? extends String> toStr3, E e) {
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


