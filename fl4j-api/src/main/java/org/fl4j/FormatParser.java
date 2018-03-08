package org.fl4j;

/**
 * Defines contract of any format parser.
 * Created by alexander on 2/12/18.
 */
public interface FormatParser {
    /**
     * Parsers format spec and returns array of functions that can be later applied to the array of arguments.
     * @param format the format specification
     * @return array of functions that transform arguments ot their string representations
     */
    Object[] parse(String format);
}
