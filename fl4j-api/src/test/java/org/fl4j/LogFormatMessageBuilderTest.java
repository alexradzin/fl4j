package org.fl4j;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test verifies both {@link LogFormatParser} and {@link MessageBuilder}
 * to make sure these two can work together
 * Created by alexander on 2/13/18.
 */
class LogFormatMessageBuilderTest {
    @Test
    void formatWithoutPlaceholders() {
        assertEquals("Hello world", test("Hello world", 123));
    }

    @Test
    void manyParts() {
        assertEquals("Hello Bill, you have 314 messages from 217 people!", test("Hello {}, you have {} messages from {} people!", "Bill", 314, 217));
    }


    private String test(String fmt, Object ... args) {
        Object[] template = new LogFormatParser().parse(fmt);
        MessageBuilder mb = new MessageBuilder();
        StringBuilder buf = new StringBuilder();

        for (int i = 0, index = i; i < args.length; i++) {
            index = mb.append(buf, template, index, args[i]);
        }
        return buf.toString();
    }
}
