package org.fl4j;

/**
 * Empty implementation of {@link LogProvider}. Used as a fallback when no other implementations are available.
 * Created by alexander on 3/6/18.
 */
public class EmptyLogProvider implements LogProvider {
    private Log log = new NoOpLog();

    @Override
    public Log create(LogBuilder builder) {
        return log;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
}
