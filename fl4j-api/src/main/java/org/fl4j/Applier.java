package org.fl4j;

import java.util.function.Function;

/**
 * Invokes function with given argument safely.
 * {@link Function} is an interface that declares 1 method that gets 1 argument and returns value.
 * There can be 2 types of implementations:
 * <ol>
 *     <li>The argument can be sent to other function (probably static), e.g. {@code String.valueOf(arg)}</li>
 *     <li>The argument may be used as an object to invoke method on, e.g. {@code arg.toString()}</li>
 * </ol>
 * In first case the responsibility is on the function implementor. However in the second case the {@link NullPointerException}
 * will be thrown if {@code arg} is {@code null}.
 * Created by alexander on 2/14/18.
 */
public class Applier {
    public static <T, R> R apply(Function<T, R> f, T arg) {
        if (arg == null) {
            try {
                return f.apply(null);
            } catch (NullPointerException e) {
                return null;
            }
        }
        return f == null ? null : f.apply(arg);
    }
}
