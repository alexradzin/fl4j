package org.fl4j;

import org.fl4j.util.ExceptionMatcher;
import org.fl4j.util.ToStringMatcher;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Unit test of {@link LoggerAdapterBase}
 * Created by alexander on 3/11/18.
 */
class LoggerAdapterBaseTest {
    @Test
    void logString() {
        test(l -> l.log("hello"), "hello", null, "hello");
    }

    @Test
    void logOneStringArg() {
        test(l -> l.log("Hello, {}!", "world"), "Hello, world!", null, "world");
    }

    @Test
    void logOneIntArg() {
        test(l -> l.log("And the answer is {}", 42), "And the answer is 42", null, 42);
    }

    @Test
    void logOneIntArgWithFunction() {
        test(l -> l.log("And the answer is {}", 42, (n) -> "" + (n * 2)) , "And the answer is 84", null, 42);
    }

    @Test
    void logOneIntArgWithFunctionAndSecondArg() {
        List<Integer> list = asList(1, 2, 3);
        test(l -> l.log("List is: {} contains {} elements", list, Object::toString, list.size()) , "List is: [1, 2, 3] contains 3 elements", null, list);
        test(l -> l.log("List is: sum={} length={}", list, (list1) -> "" + ((Integer) list1.stream().mapToInt(Integer::intValue).sum()).toString(), list.size()) , "List is: sum=6 length=3", null, list);
        test(l -> l.log("List is: {} contains {} elements", list, list.size()) , "List is: [1, 2, 3] contains 3 elements", null, list);
        test(l -> l.log("Number {} contains digit {} and ends with {}", 12345, () -> "3", () -> "5"), "Number 12345 contains digit 3 and ends with 5", null, 12345);
        test(l -> l.log("PI={}, E={} {}", 3.14, 2.71828, () -> "etc."), "PI=3.14, E=2.71828 etc.", null, 3.14);
        test(l -> l.log("PI={}, SQUIRE={}, E={}", 3.14, () -> "" + (2 * 3.14 * 3), 2.71828), "PI=3.14, SQUIRE=18.84, E=2.71828", null, 3.14);
    }


    @Test
    void exceptionOneIntArg() {
        test(l -> l.exception("And the answer is {}", 42, new IllegalArgumentException()) , "And the answer is 42", new IllegalArgumentException(), 42);
    }


    private <T> void test(Function<Log, T> f, String expectedMessage, Throwable expectedException, T expectedResult) {
        LoggerAdapterBase log = spy(LoggerAdapterBase.class);
        assertEquals(expectedResult, f.apply(log));
        verify(log).write(fmtThat(expectedMessage), exThat(expectedException));
    }

    private static <T> T fmtThat(String fmt) {
        return argThat(new ToStringMatcher<>(fmt));
    }

    private static <E extends Throwable> E exThat(E e) {
        return argThat(new ExceptionMatcher<>(e));
    }
}