package org.fl4j;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test of {@link Getter}
 * Created by alexander on 3/11/18.
 */
class GetterTest {
    @Test
    void string() {
        test(() -> "foo", "foo");
    }

    @Test
    void integer() {
        test(() -> 1 + 2, 3);
    }

    @Test
    void nullSupplier() {
        test(null, null);
    }


    @SuppressWarnings({"ConstantConditions", "ConstantIfStatement"}) // the simplest way to throw exception from supplier
    @Test
    void supplierThatThrowsNPE() {
        test(() -> {if (true) {throw new NullPointerException("");} return 0;}, null);
    }


    private <R> void test(Supplier<R> s, R exp) {
        assertEquals(exp, Getter.get(s));
    }
}