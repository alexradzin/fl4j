package org.fl4j;

import java.util.function.Function;

import static java.lang.String.format;

/**
 * Convenience utility class that provides functional approach to formatting of primitives.
 * Created by alexander on 2/11/18.
 */
public class Formatter {
    private static final Function<Object, String> FS = o -> format("%s", o);
    private static final Function<Number, String> FD = n -> format("%d", n);
    private static final Function<Number, String> FF = f -> format("%f", f);




    public static <T extends Number> Function<T, String> ff(int i, int d) {
        return value -> format("%" + i + "."  + d + "f", value);
    }

    public static <T extends Number> Function<T, String> ff(int d) {
        return value -> format("%" + "."  + d + "f", value);
    }

    public static <T extends Number> Function<T, String> ffl(int i, int d) {
        return value -> format("%-" + i + "."  + d + "f", value);
    }
    public static <T extends Number> Function<T, String> ffl(int d) {
        return value -> format("%-" + "."  + d + "f", value);
    }

    public static <T extends Number> Function<T, String> fi(int i) {
        return value -> format("%" + i + "d", value);
    }

    public static <T extends Number> Function<T, String> fil(int i) {
        return value -> format("%-" + i + "d", value);
    }

    public static <T extends Number> Function<T, String> fi0(int i) {
        return value -> format("%0" + i + "d", value);
    }

    public static <T> Function<T, String> fs(int i) {
        return value -> format("%" + i + "s", value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, String> fs() {
        return (Function<T, String>)FS;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> Function<T, String> fd() {
        return (Function<T, String>)FD;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> Function<T, String> ff() {
        return (Function<T, String>)FF;
    }

    public static <T> Function<T, String> f(String fmt) {
        return value -> format(fmt, value);
    }
}
