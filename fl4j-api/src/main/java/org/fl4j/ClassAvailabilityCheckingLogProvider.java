package org.fl4j;

/**
 * Convenient base class for {@link LogProvider} implementations that check implementation availability
 * by checking availability of one of the key classes.
 * Created by alexander on 3/5/18.
 */
public abstract class ClassAvailabilityCheckingLogProvider implements LogProvider {
    private final String classToCheck;
    private Boolean available;

    public ClassAvailabilityCheckingLogProvider(String classToCheck) {
        this.classToCheck = classToCheck;
    }


    @Override
    public boolean isAvailable() {
        if (available == null) {
            available = checkAvailability();
        }
        return available;
    }

    private boolean checkAvailability() {
        try {
            Thread.currentThread().getContextClassLoader().loadClass(classToCheck);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
