package org.fl4j;

/**
 * Log provider is an adaptor between FL4J core and concrete logging implementations
 * Created by alexander on 2/4/18.
 */
public interface LogProvider {
    /**
     * Creates insance of log using parameters taken from provided {@link LogBuilder}
     * @param builder log builder
     * @return the log instance
     */
    Log create(LogBuilder builder);

    /**
     * Checks whether the log implementation is really available.
     * @return true if log implementation is available, false otherwise
     */
    boolean isAvailable();

    /**
     * Log priority that is used by {@link LogBuilder} to choose log provider when several are available.
     * The less this number the higher priority, i.e. priority 1 is preferable on priority 10.
     * @return the priority of log provider
     */
    int getPriority();
}
