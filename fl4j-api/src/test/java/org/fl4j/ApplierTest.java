package org.fl4j;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.fl4j.Applier.apply;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test of {@link Applier}
 * Created by alexander on 3/11/18.
 */
class ApplierTest {
    @Test
    void stringToString() {
        test(Object::toString, "hello", "hello");
    }

    @Test
    void intToString() {
        test(Object::toString, 123, "123");
    }

    @Test
    void nullToString() {
        test(Object::toString, null, null);
    }

    @Test
    void nullFunction() {
        test(null, "null function", null);
    }


    private <T, R> void test(Function<T, R> f, T arg, T exp) {
        assertEquals(exp, apply(f, arg));
    }

}