package org.fl4j.util;

import org.mockito.ArgumentMatcher;

import java.util.Objects;

/**
 * Implementation of {@link org.hamcrest.Matcher} convenient for matcing of exception.
 * 2 exceptions are considered being matched if their classes and messages are equal.
 * Created by alexander on 3/12/18.
 */
public class ExceptionMatcher<E extends Throwable> extends ArgumentMatcher<E> {
    private final E e;

    public ExceptionMatcher(E e) {
        this.e = e;
    }

    @Override
    public boolean matches(Object argument) {
        if (e == null) {
            return argument == null;
        }
        @SuppressWarnings("unchecked")
        E t  = (E)argument;
        return t.getClass().equals(e.getClass()) && Objects.equals(t.getMessage(), e.getMessage());
    }
}
