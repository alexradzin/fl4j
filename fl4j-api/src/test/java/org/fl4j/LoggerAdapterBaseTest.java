package org.fl4j;

import org.fl4j.util.ExceptionMatcher;
import org.fl4j.util.ToStringMatcher;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Unit test of {@link LoggerAdapterBase}
 * Created by alexander on 3/11/18.
 */
class LoggerAdapterBaseTest {
    @Test
    void logWithReturn() {
        List<Integer> list = asList(1, 2, 3);
//        test(l -> l.log("hello"), "hello", null, "hello");
        test(l -> l.log("Hello, {}!", "world"), "Hello, world!", null, "world");
        test(l -> l.log("And the answer is {}", 42), "And the answer is 42", null, 42);
        test(l -> l.log("And the answer is {}", 42, (n) -> (n * 2)) , "And the answer is 84", null, 42);
        test(l -> l.log("List is: {} contains {} elements", list, Object::toString, list.size()) , "List is: [1, 2, 3] contains 3 elements", null, list);
        test(l -> l.log("List is: sum={} length={}", list, (list1) -> ((Integer) list1.stream().mapToInt(Integer::intValue).sum()).toString(), list.size()) , "List is: sum=6 length=3", null, list);
        test(l -> l.log("List is: {} contains {} elements", list, list.size()) , "List is: [1, 2, 3] contains 3 elements", null, list);
        test(l -> l.log("Number {} contains digit {} and ends with {}", 12345, () -> 3, () -> 5), "Number 12345 contains digit 3 and ends with 5", null, 12345);
        test(l -> l.log("PI={}, E={} {}", 3.14, 2.71828, () -> "etc."), "PI=3.14, E=2.71828 etc.", null, 3.14);
        test(l -> l.log("PI={}, SQUIRE={}, E={}", 3.14, () -> (2 * 3.14 * 3), 2.71828), "PI=3.14, SQUIRE=18.84, E=2.71828", null, 3.14);
        test(l -> l.log("n={}; n+3={}, n*3={}", 42, i -> i, () -> (42 + 3), () -> 42 * 3), "n=42; n+3=45, n*3=126", null, 42);
        test(l -> l.log("n={}; n+3={}, n*3={}", 42, i -> i, 42 + 3, () -> 42 * 3), "n=42; n+3=45, n*3=126", null, 42);
        test(l -> l.log("n={}; n+3={}, n*3={}", 42, i -> i, () -> (42 + 3), 42 * 3), "n=42; n+3=45, n*3=126", null, 42);
        test(l -> l.log("n*2={}, n-2={}", 42, i -> i*2, () -> (42 - 2)), "n*2=84, n-2=40", null, 42);
        test(l -> l.log("n={}, n-2={}", 42, () -> (42 - 2)), "n=42, n-2=40", null, 42);
        test(l -> l.log("n*2={}, pi={}, e={}", 21, i -> i*2, 3.14, 2.7), "n*2=42, pi=3.14, e=2.7", null, 21);
        test(l -> l.log("n={}, pi={}, e={}", 42, 3.14, 2.7), "n=42, pi=3.14, e=2.7", null, 42);
    }


    @Test
    void exceptionOneIntArg() {
        test(l -> l.exception("And the answer is {}", 42, new IllegalArgumentException()) , "And the answer is 42", new IllegalArgumentException(), 42);
    }


    @Test
    void rtExceptionWithoutMessageWithoutCauseWithReturn() {
        exceptionWithReturn(new IllegalArgumentException());
    }

    @Test
    void rtExceptionWithMessageWithoutCauseWithReturn() {
        exceptionWithReturn(new IllegalArgumentException("Wrong argument"));
    }

    @Test
    void rtExceptionWithMessageWithCauseWithReturn() {
        exceptionWithReturn(new IllegalArgumentException("Wrong argument", new NullPointerException("null")));
    }

    private void exceptionWithReturn(Throwable e) {
        List<Integer> list = asList(1, 2, 3);
        test(l -> l.exception("Hello, {}!", "exception", e), "Hello, exception!", e, "exception");
        test(l -> l.exception("And the answer is {}", 42, e), "And the answer is 42", e, 42);
        test(l -> l.exception("And the answer is {}", 42, (n) -> (n * 2), e) , "And the answer is 84", e, 42);
        test(l -> l.exception("List is: {} contains {} elements", list, Object::toString, list.size(), e) , "List is: [1, 2, 3] contains 3 elements", e, list);
        test(l -> l.exception("List is: sum={} length={}", list, (list1) -> ((Integer) list1.stream().mapToInt(Integer::intValue).sum()).toString(), list.size(), e) , "List is: sum=6 length=3", e, list);
        test(l -> l.exception("List is: {} contains {} elements", list, list.size(), e) , "List is: [1, 2, 3] contains 3 elements", e, list);
        test(l -> l.exception("Number {} contains digit {} and ends with {}", 12345, () -> 3, () -> 5, e), "Number 12345 contains digit 3 and ends with 5", e, 12345);
        test(l -> l.exception("PI={}, E={} {}", 3.14, 2.71828, () -> "etc.", e), "PI=3.14, E=2.71828 etc.", e, 3.14);
        test(l -> l.exception("PI={}, SQUIRE={}, E={}", 3.14, () -> (2 * 3.14 * 3), 2.71828, e), "PI=3.14, SQUIRE=18.84, E=2.71828", e, 3.14);
        test(l -> l.exception("n={}; n+3={}, n*3={}", 42, i -> i, () -> (42 + 3), () -> 42 * 3, e), "n=42; n+3=45, n*3=126", e, 42);
        test(l -> l.exception("n={}; n+3={}, n*3={}", 42, i -> i, 42 + 3, () -> 42 * 3, e), "n=42; n+3=45, n*3=126", e, 42);
        test(l -> l.exception("n={}; n+3={}, n*3={}", 42, i -> i, () -> (42 + 3), 42 * 3, e), "n=42; n+3=45, n*3=126", e, 42);
        test(l -> l.exception("n={}; n+3={}", 42, i -> i, () -> (42 + 3), e), "n=42; n+3=45", e, 42);
        test(l -> l.exception("n={}; e={}, pi={}", 42, i -> i, 2.7, 3.14, e), "n=42; e=2.7, pi=3.14", e, 42);
        test(l -> l.exception("n={}; e={}, pi={}", 42, 2.7, 3.14, e), "n=42; e=2.7, pi=3.14", e, 42);
    }

    @Test
    void testVoidLog() {
        testVoid(l -> l.log("Numbers: {} and {}", () -> 1, () -> 2), "Numbers: 1 and 2", null);
        testVoid(l -> l.log("{} * {} = {}", () -> 3, () -> 4, () -> 12), "3 * 4 = 12", null);
        testVoid(l -> l.log("{} * {} = {}", () -> 3, () -> 4, () -> 12), "3 * 4 = 12", null);
    }

    @Test
    void testVoidException() {
        testVoid(l -> l.exception("Exception happened", new IllegalStateException()), "Exception happened", new IllegalStateException());
        testVoid(l -> l.exception("Numbers: {} and {}", () -> 1, () -> 2, new IllegalStateException()), "Numbers: 1 and 2", new IllegalStateException());
        testVoid(l -> l.exception("{} * {} = {}", () -> 3, () -> 4, () -> 12, new IllegalStateException()), "3 * 4 = 12", new IllegalStateException());
        testVoid(l -> l.exception("{} * {} = {}", () -> 3, () -> 4, () -> 12, new IllegalStateException()), "3 * 4 = 12", new IllegalStateException());
        testVoid(l -> l.exception("{} < {}", 3, () -> 4, new IllegalStateException()), "3 < 4", new IllegalStateException());
    }


    @Test
    void testManyWithoutArgs() {
        test(l -> l.all("hello"), "hello", null, null);
    }

    @Test
    void testMany() {
        LoggerAdapterBase log = spy(LoggerAdapterBase.class);
        log.all("{}-{}-{}-{}", 1, 2, 3, 4);
        verify(log).write(fmtThat("1-2-3-4"), exThat(null));
    }

    @Test
    void testManyWithException() {
        LoggerAdapterBase log = spy(LoggerAdapterBase.class);
        log.all("error: ", new RuntimeException());
        verify(log).write(fmtThat("error: "), exThat(new RuntimeException()));
    }

    @Test
    void testManyWithArgsAndException() {
        LoggerAdapterBase log = spy(LoggerAdapterBase.class);
        log.all("{}-{}-{}", 1, 2, 3, new RuntimeException());
        verify(log).write(fmtThat("1-2-3"), exThat(new RuntimeException()));
    }

    @Test
    void testManyWithFunction() {
        LoggerAdapterBase log = spy(LoggerAdapterBase.class);
        log.all("{}-{}-{}", 1, (Function<Integer, Integer>) i -> i + i, 3, 4);
        verify(log).write(fmtThat("2-3-4"), exThat(null));
    }

    @Test
    void testManyWithSupplier() {
        LoggerAdapterBase log = spy(LoggerAdapterBase.class);
        log.all("{}-{}-{}-{}", (Supplier<Integer>) () -> 12, (Supplier<Integer>) () -> 23, (Supplier<Integer>) () -> 34, (Supplier<Integer>) () -> 45);
        verify(log).write(fmtThat("12-23-34-45"), exThat(null));
    }

    @Test
    void testManyWithFunctionAndSupplier() {
        LoggerAdapterBase log = spy(LoggerAdapterBase.class);
        log.all("{}-{}-{}-{}-{}", 8, (Function<Integer, Integer>) i -> i + i, (Supplier<Integer>) () -> 12, (Supplier<Integer>) () -> 23, (Supplier<Integer>) () -> 34, (Supplier<Integer>) () -> 45);
        verify(log).write(fmtThat("16-12-23-34-45"), exThat(null));
    }


    @Test
    void isEnabled() {
        LoggerAdapterBase log = spy(LoggerAdapterBase.class);
        assertTrue(log.isEnabled());
    }

    private <T> void test(Function<Log, T> f, String expectedMessage, Throwable expectedException, T expectedResult) {
        LoggerAdapterBase log = spy(LoggerAdapterBase.class);
        assertEquals(expectedResult, f.apply(log));
        verify(log).write(fmtThat(expectedMessage), exThat(expectedException));
    }

    private void testVoid(Consumer<Log> f, String expectedMessage, Throwable expectedException) {
        LoggerAdapterBase log = spy(LoggerAdapterBase.class);
        f.accept(log);
        verify(log).write(fmtThat(expectedMessage), exThat(expectedException));
    }


    private static <T> T fmtThat(String fmt) {
        return argThat(new ToStringMatcher<>(fmt));
    }

    private static <E extends Throwable> E exThat(E e) {
        return argThat(new ExceptionMatcher<>(e));
    }
}