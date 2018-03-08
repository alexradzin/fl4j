package org.fl4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Implementation of {@link FormatParser} that parses format with braces (SLF4J style).
 * Created by alexander on 2/12/18.
 */
public class LogFormatParser implements FormatParser {
    // VisibleForTests
    static final Function<Object, String> VALUE_OF = String::valueOf;
    @Override
    public Object[] parse(String format) {
        List<Object> result = new ArrayList<>();
        int prev = 0;
        char[] chars = format.toCharArray();
        int i = 0;
        for (; i < chars.length - 1; i++) {
            if (chars[i] == '{' && chars[i+1] == '}') {
                if (i > 0 && chars[i-1] == '\\' && !(i > 1 && chars[i-2] == '\\')) {
                    continue;
                }
                result.add(new String(chars, prev, i - prev));
                result.add(VALUE_OF);
                prev = i + 2;
            }
        }

        if (prev < chars.length) {
            result.add(new String(chars, prev, chars.length - prev));
        }
        return result.toArray(new Object[result.size()]);
    }
}
