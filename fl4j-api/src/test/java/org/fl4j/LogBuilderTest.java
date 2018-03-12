package org.fl4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit test of {@link LogBuilder}
 * Created by alexander on 3/11/18.
 */
class LogBuilderTest {
    @BeforeEach
    void init() {
        LogBuilder.setLogProvider(null);
    }

    @Test
    void createDefaultLog() {
        Log log = LogBuilder.builder().build();
        assertNotNull(log);
        assertEquals(NoOpLog.class, log.getClass());
        assertFalse(log.isEnabled());
    }

    @Test
    void createDebugLog() {
        Log log = LogBuilder.builder().withLevel(LogLevel.DEBUG).build();
        assertNotNull(log);
        assertEquals(NoOpLog.class, log.getClass());
        assertFalse(log.isEnabled());
    }

    @Test
    void useCustomLogProvider() {
        LogBuilder.setLogProvider(new TestLogProvider());
        Log log = LogBuilder.builder().withLevel(LogLevel.ERROR).build();
        assertNotNull(log);
        String logClassName = log.getClass().getName();
        assertTrue(logClassName.startsWith(Log.class.getName()));
        assertTrue(logClassName.contains("Mockito"));
        assertTrue(log.isEnabled());
    }


    public static class TestLogProvider implements LogProvider {

        @Override
        public Log create(LogBuilder builder) {
            Log log = Mockito.mock(Log.class);
            when(log.isEnabled()).thenReturn(true);
            return log;
        }

        @Override
        public boolean isAvailable() {
            return true;
        }

        @Override
        public int getPriority() {
            return 1;
        }
    }
}