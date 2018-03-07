package org.fl4j;


import java.util.*;

import static java.lang.Thread.currentThread;

/**
 * Created by alexander on 2/4/18.
 */
public class LogBuilder {
    private static final String className = LogBuilder.class.getName();
    private static final String FL4J_PROVIDER = "fl4j.provider";
    private String name;
    private LogLevel level = LogLevel.INFO;
    private static LogProvider provider;  // TODO: initialize


    //TODO: remove this once initialization is implemented
    public static void setLogProvider(LogProvider provider) {
        LogBuilder.provider = provider;
    }

    private LogBuilder() {

    }

    public static LogBuilder builder() {
        return new LogBuilder();
    }

    public LogBuilder withLevel(LogLevel level) {
        this.level = level;
        return this;
    }

    public LogBuilder named(String name) {
        this.name = name;
        return this;
    }

    public LogBuilder forClass(Class<?> clazz) {
        return named(clazz.getName());
    }

    private String discoverName() {
        if (name != null) {
            return name;
        }

        return Arrays.stream(currentThread().getStackTrace()).
                skip(1).
                map(StackTraceElement::getClassName).
                filter(c -> !className.equals(c)).
                findFirst().
                orElse("org.fl4j");
    }

    public Log build() {
        name = discoverName();
        return getProvider().create(this);
    }

    private LogProvider getProvider() {
        if (provider == null) {
            List<LogProvider> providers = findServiceProviders();
            String configuredProvider = System.getProperty(FL4J_PROVIDER);
            if (configuredProvider != null) {
                Optional<LogProvider> op = providers.stream().filter(p -> p.getClass().getName().equals(configuredProvider)).findFirst();
                if (op.isPresent()) {
                    provider = op.get();
                    return provider;
                } else {
                    System.err.printf("Log Provider %s is not found%n", configuredProvider);
                }
            }
            if (!providers.isEmpty()) {
                provider = Collections.min(providers, new Comparator<LogProvider>() {
                    @Override
                    public int compare(LogProvider p1, LogProvider p2) {
                        return p1.getPriority() - p2.getPriority();
                    }
                });

                if (provider instanceof EmptyLogProvider) {
                    System.out.println("No log provider is found");
                }

                return provider;
            } else {
                System.out.println("No log provider is found");
            }
        }
        return provider;
    }

    public String getLogName() {
        return name;
    }

    public LogLevel getLogLevel() {
        return level;
    }

    // TODO: re-write the code functionally
    private static List<LogProvider> findServiceProviders() {
        ServiceLoader<LogProvider> serviceLoader = ServiceLoader.load(LogProvider.class);
        List<LogProvider> providerList = new ArrayList<>();
        for (LogProvider provider : serviceLoader) {
            if (provider.isAvailable()) {
                providerList.add(provider);
            }
        }
        return providerList;
    }

}
