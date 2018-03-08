package org.fl4j;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test of {@link MessageBuilder}
 * Created by alexander on 2/13/18.
 */
class MessageBuilderTest {
    @Test
    void emptyTemplate() {
        assertEquals("", append1(new Object[0], 0, null));
        assertEquals("", append1(new Object[0], 0, 12345));
    }

    @Test
    void plain() {
        String text = "hello";
        Object[] template = new Object[] {"hello"};
        assertEquals(text, append1(template, 0, null));
        assertEquals(text, append1(template, 0, 12345));
    }

    @Test
    void placeholderOnly() {
        Function<Object, String> f = String::valueOf;
        Object[] template = new Object[] {"", f};
        assertEquals("null", append1(template, 0, null));
        assertEquals("12345", append1(template, 0, 12345));
    }

    @Test
    void placeholderInTheMiddle() {
        Function<Object, String> f = String::valueOf;
        Object[] template = new Object[] {"Hello, ", f, "!"};
        assertEquals("Hello, world!", append1(template, 0, "world"));
    }

    @Test
    void placehodlerInTheEnd() {
        Function<Object, String> f = String::valueOf;
        Object[] template = new Object[] {"Hello, ", f};
        assertEquals("Hello, world", append1(template, 0, "world"));
    }


//    @Test
//    void severalPlaceholders() {
//        Function<Object, String> f = String::valueOf;
//        Object[] template = new Object[] {"Hello, ", f, " and ", f, "!"};
//        append1(template, 0, "Bonnie");
//        String result = append1(template, 1, "Clyde");
//        assertEquals("Hello, Bonnie and Clyde!", result);
//    }

    @Test
    void appendSeveralArgs() {
        MessageBuilder b = new MessageBuilder();
        StringBuilder buf = new StringBuilder();
        Function<Object, String> f = String::valueOf;
        Object[] template = new Object[] {"Hello ", f, ", you have ", f, " letters from ", f, " people!"};

        int i = 0;
        i = b.append(buf, template, i, "John");
        i = b.append(buf, template, i, 42);
        i = b.append(buf, template, i, 10);

        assertEquals("Hello John, you have 42 letters from 10 people!", buf.toString());
        assertEquals(-1, i);
    }

    private String append1(Object[] template, int index, Object arg) {
        MessageBuilder b = new MessageBuilder();
        StringBuilder buf = new StringBuilder();
        b.append(buf, template, index, arg);
        return buf.toString();
    }

}

