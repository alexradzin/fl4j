package org.fl4j;

import org.junit.jupiter.api.Test;

import static org.fl4j.LogFormatParser.VALUE_OF;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Unit test of {@link LogFormatParser}
 */
class LogFormatParserTest {
    @Test
    void emptyFormat() {
        test("", new Object[0]);
    }

    @Test
    void textOnly() {
        test("Hello, world!", new Object[] {"Hello, world!"});
    }

    @Test
    void oneArgTail() {
        test("Hello, {}", new Object[] {"Hello, ", VALUE_OF});
    }

    @Test
    void oneArgMiddle() {
        test("Hello, {}!", new Object[] {"Hello, ", VALUE_OF, "!"});
    }

    @Test
    void oneArgHead() {
        test("{}, you have a message.", new Object[] {"", VALUE_OF, ", you have a message."});
    }

    @Test
    void twoArgsHeadTail() {
        test("{}, you have a message from {}", new Object[] {"", VALUE_OF, ", you have a message from ", VALUE_OF});
    }

    @Test
    void twoArgsHeadMiddle() {
        test("{}, you have a message from {}!", new Object[] {"", VALUE_OF, ", you have a message from ", VALUE_OF, "!"});
    }

    @Test
    void threeArgsHeadMiddleTail() {
        test("{}, you have {} messages from {}!", new Object[] {"", VALUE_OF, ", you have ", VALUE_OF, " messages from ", VALUE_OF, "!"});
    }

    @Test
    void threeArgsHeadMiddle() {
        test("Hi {}, you have {} messages from {} your friends.", new Object[] {"Hi ", VALUE_OF, ", you have ", VALUE_OF, " messages from ", VALUE_OF, " your friends."});
    }

    @Test
    void sequentialArgs() {
        test("{}{}", new Object[] {"", VALUE_OF, "", VALUE_OF});
    }

    private void test(String fmt, Object[] expected) {
        assertArrayEquals(expected, new LogFormatParser().parse(fmt));
    }
}