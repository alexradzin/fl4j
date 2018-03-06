package org.fl4j;

/**
 * Created by alexander on 3/6/18.
 */
public class EmptyLogProvider implements LogProvider {
    private Log log = new NoOpLog();

    public EmptyLogProvider() {
        System.err.println("No log provider is found");
    }

    @Override
    public Log create(LogBuilder builder) {
        return log;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
