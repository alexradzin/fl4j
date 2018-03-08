package org.fl4j;

import java.util.function.Supplier;

/**
 * Utility class that  provides null-safe access to {@link Supplier}
 * Created by alexander on 3/1/18.
 */
public class Getter {
    /**
     * Calls {@link Supplier#get()} using null-safe manner.
     * If given supplier is null just returns null. Otherwise calls {@link Supplier#get()}
     * wrapping it by try/catch block and catching {@link NullPointerException}.
     * @param supplier the supplier
     * @param <R> the type of supplier
     * @return result of {@link Supplier#get()} or {@code null} if supplier is null or call throws {@link NullPointerException}
     */
    public static <R> R get(Supplier<R> supplier) {
        if (supplier == null) {
            return null;
        }
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
