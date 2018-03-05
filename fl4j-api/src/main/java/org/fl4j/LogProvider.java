package org.fl4j;

/**
 * Created by alexander on 2/4/18.
 */
public interface LogProvider {
    Log create(LogBuilder builder);
    boolean isAvailable();
}
