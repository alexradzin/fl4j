package org.fl4j;

import java.util.function.Supplier;

/**
 * Created by alexander on 3/1/18.
 */
public class Getter {
    public static <R> R get(Supplier<R> f) {
        if (f == null) {
            return null;
        }
        try {
            return f.get();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
