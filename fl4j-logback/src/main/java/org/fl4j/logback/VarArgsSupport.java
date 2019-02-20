package org.fl4j.logback;

import ch.qos.logback.classic.Logger;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Helper class for loggers that provide log method with var args.
 * Created by alexander on 3/19/18.
 */
class VarArgsSupport {
    VarArgsSupport() {
    }

    <T> T all(Logger logger, String fmt, Object ... args) {
        @SuppressWarnings("unchecked")
        T result = args.length == 0 ? null : (T)args[0];

        boolean shift = false;
        if (args.length > 1 && args[1] instanceof Function) {
            @SuppressWarnings("unchecked")
            Object value = ((Function<Object, String>)args[1]).apply(args[0]);
            args[1] = value;
            shift = true;
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Supplier) {
                //noinspection unchecked
                Object value = ((Supplier<Object>)args[i]).get();
                args[i] = value;
            }
        }

        Object[] args2log = args;
        if (shift) {
            args2log = new Object[args.length - 1];
            System.arraycopy(args, 1, args2log, 0, args2log.length);
        }

        logger.info(fmt, args2log);
        return result;
    }
}
