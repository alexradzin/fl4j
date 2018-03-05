package org.fl4j.demo;

import static java.lang.String.format;

/**
 * Created by alexander on 2/10/18.
 */
public class IfPerf {
    private boolean debug = false;


    private long calc(int n, int m) {
        long total = 0;
        for (int i = 0;  i < n; i++) {
            long sum = 0;
            long before = System.currentTimeMillis();
            for (int j = 0;  j < m; j++) {
                sum += j;
            }
            long after = System.currentTimeMillis();
            System.out.println(format("Attempt %d: %d ms", i, (after - before)));
            total += sum;
        }
        return total;
    }



    private long calcWithIf(int n, int m) {
        long total = 0;
        for (int i = 0;  i < n; i++) {
            long sum = 0;
            long before = System.currentTimeMillis();
            for (int j = 0;  j < m; j++) {
                sum += j;
                if (isDebugEnabled()) {
                    System.out.println(format("Counter %d: sum: %d", j, sum));
                }
            }
            long after = System.currentTimeMillis();
            System.out.println(format("Attempt %d: %d ms", i, (after - before)));
            total += sum;
        }
        return total;
    }

    private boolean isDebugEnabled() {
        return debug;
    }


    long checkType(Object param, long n) {
        long sum = 0;
        long before = System.currentTimeMillis();
        for (long i = 0; i < n; i++) {
            if (param instanceof String) {
                sum += 1;
            }
        }
        long after = System.currentTimeMillis();
        return after - before + sum - sum;
    }

    long cast(Object param, long n) {
        long sum = 0;
        long before = System.currentTimeMillis();
        for (long i = 0; i < n; i++) {
            if (param instanceof String) {
                String s = (String)param;
                sum += 1;
            }
        }
        long after = System.currentTimeMillis();
        return after - before + sum - sum;
    }

    long sum(Object param, long n) {
        long sum = 0;
        long before = System.currentTimeMillis();
        for (long i = 0; i < n; i++) {
            sum += 1;
        }
        long after = System.currentTimeMillis();
        return after - before + sum - sum;
    }


    public static void main(String[] args) {
        IfPerf instance = new IfPerf();
//        instance.debug = Boolean.parseBoolean(System.getProperty("debug", "false"));
//        //long res1 = instance.calc(10, 1_000_000_000);
//        //long res2 = instance.calcWithIf(10, 1_000_000_000, Boolean.parseBoolean(System.getProperty("debug", "false")));
//        long res2 = instance.calcWithIf(10, 1_000_000_000);
//        //System.out.println("res1=" + res1);
//        System.out.println("res2=" + res2);


        long n = 10_000_000_000L;
        System.out.println("sum:        " + instance.sum("", n));
        System.out.println("check type: " + instance.checkType("", n));
        System.out.println("cast:       " + instance.cast("", n));



    }
}
