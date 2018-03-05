package org.fl4j.demo;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.fl4j.Formatter;
import org.fl4j.Log;
import org.fl4j.LogBuilder;
import org.fl4j.LogLevel;
import org.fl4j.log4j.Log4j2Provider;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.LogManager;


/**
 * Created by alexander on 2/1/18.
 */
public class Demo {
    static {
        //LogBuilder.setLogProvider(new LogBackProvider());
        //LogBuilder.setLogProvider(new JuliLogProvider());
        //LogBuilder.setLogProvider(new Log4jProvider());
        //LogBuilder.setLogProvider(new Log4j2Provider());
    }
    private final static Log error = LogBuilder.builder().withLevel(LogLevel.ERROR).build();
    private final static Log info = LogBuilder.builder().build();
    private final static Log debug = LogBuilder.builder().withLevel(LogLevel.DEBUG).build();
    private final static Log trace = LogBuilder.builder().withLevel(LogLevel.TRACE).build();
    private final static Log event = LogBuilder.builder().named("Event").build();


    private static final Logger root = org.apache.logging.log4j.LogManager.getRootLogger();
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(Demo.class.getName());

    public void bar() {
       // LoggerFactory.getLogger(getClass()).info("aaaaaaaaaaaaaaaaaaaaaa");
        info.log("Prints object using its toString", this, Object::toString);
        info.log("Prints object using its toString", this, new Exception());
        Function<Demo, String> s = Object::toString;
        info.log("Prints object using its toString", this, s);
        info.log("Prints object using its toString", this, Object::toString);


        LogManager.getLogManager().getLogger("f");
        LogManager.getLogManager().addPropertyChangeListener(null);

    }

    public long foo() {
        info.log("Prints object using its toString {}", this, Object::toString);

        info.log("Prints object using its hashCode {}", this, o -> String.valueOf(o.hashCode()));

        Integer nullInt = System.currentTimeMillis() > 10 ? null : 123;
        info.log("Prints object using its toString {} and null using String::valueOf {}", this, Object::toString, () -> String.valueOf(nullInt));


        info.log("numbers {} {}", 1, 2);
        info.log("number and expression n^2 = {}", 2, integer -> "" + integer * integer);
        info.log("object with custom function {}", this, Demo::mkStr);

        info.log("string with explicitly specified toString {}", "abc", Object::toString);
        info.log("string's length {}", "abc", s -> "" + s.length());
        info.log("sum n+n={}", 1, i -> "" + (i+i));

        info.log("two numbers {} {}", 1, 3.14);


        try {
            throw new RuntimeException("Ooops!");
        } catch (RuntimeException e) {
            error.exception("Exception was thrown", e);
        }


        debug.log("Debug should not be visible");
        if (debug.isEnabled()) {
            info.log("Debug is ebabled");
        }
        info.log("Debug {}", debug.isEnabled());
        info.log("Trace {}", trace.isEnabled());
        if (trace.isEnabled()) {
            info.log("Trace is ebabled");
        }
        trace.log("Trace should not be visible");


        info.log("PI={}", Math.PI, Formatter.ff(10, 2));
        info.log("PI={}", Math.PI, Formatter.ffl(10, 2));

        info.log("number={};", 12345, Formatter.fi(20));
        info.log("number={};", 12345, Formatter.fil(20));
        info.log("number={};", 12345, Formatter.fi0(20));


        info.log("something s: {}", "hello", Formatter.fs());
        info.log("something s(15): {}", "bye", Formatter.fs(15));


        root.info("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        root.error("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        logger.info("IIIIIIIIIIIIIIIIIIIIIIII");
        logger.log(Level.INFO, "_____IIIIIIIIIIII______");

        test(() -> calc());

        int five = info.log("Print five", 5, () -> "" + "five".length(), () -> "Five");

        return info.log("result {}", System.currentTimeMillis());
    }


    private static int calc() {
        return 123;
    }

    private static void test(Supplier<Integer> s) {
        if (false) {
            System.out.println(s.get());
        }
    }

    public static String mkStr(Demo d) {
        return d.toString();
    }

    public static void main(String[] args) {
        new Demo().foo();
    }
}
