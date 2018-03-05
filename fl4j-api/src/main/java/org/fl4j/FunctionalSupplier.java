package org.fl4j;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by alexander on 3/4/18.
 */
public class FunctionalSupplier<T, R> implements Supplier<R> {
    private final Function<T, R> f;
    private final T arg;

    public FunctionalSupplier(Function<T, R> f, T arg) {
        this.f = f;
        this.arg = arg;
    }

    @Override
    public R get() {
        return Applier.apply(f, arg);
    }
}
