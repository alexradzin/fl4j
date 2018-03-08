package org.fl4j;

import java.util.function.Function;

/**
 * Helper class that helps to build formatted message.
 * Created by alexander on 2/13/18.
 */
class MessageBuilder {
    /**
     * Appends argument to given {@code buffer} to the position identified by {@code index}
     * @param buffer the buffer to work on
     * @param template array of format templates
     * @param index position of current argument
     * @param arg value of current argument
     * @return position of the next argument or -1 if the last argument has been added.
     */
    int append(StringBuilder buffer, Object[] template, int index, Object arg) {
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
