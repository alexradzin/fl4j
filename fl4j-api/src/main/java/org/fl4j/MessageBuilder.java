package org.fl4j;

import java.util.function.Function;

/**
 * Created by alexander on 2/13/18.
 */
public class MessageBuilder {
    public int append(StringBuilder buffer, Object[] template, int index, Object arg) {
        if (index < 0 || index > template.length - 1) {
            return -1;
        }

        buffer.append(String.valueOf(template[index]));

        index++;
        if (index > template.length - 1) {
            return -1;
        }
        @SuppressWarnings("unchecked")
        Function<Object, String> function = (Function<Object, String>)template[index];
        buffer.append(function.apply(arg));

        index++;
        // last element just after function can be only string suffix
        if (index == template.length - 1) {
            buffer.append(String.valueOf(template[index]));
            return -1;
        }

        return index;
    }
}
