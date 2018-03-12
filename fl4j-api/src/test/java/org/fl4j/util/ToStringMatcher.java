package org.fl4j.util;

import org.hamcrest.Description;
import org.mockito.ArgumentMatcher;

/**
 * Implementation of {@link org.hamcrest.Matcher} convenient for matcing of exception.
 * Object is consdered matching if its {@link #toString()} returns result equals to given string.
 * Created by alexander on 3/12/18.
 */
public class ToStringMatcher<T> extends ArgumentMatcher<T> {
    private final String str;

    public ToStringMatcher(String str) {
        this.str = str;
    }

    @Override
    public boolean matches(Object argument) {
        return str == null ? argument == null : str.equals(argument.toString());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(str);
    }
}
