package io.github.dtolmachev1.benchmark;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.List;

/**
 * <p>Class for testing execution time of some methods.</p>
 */
public class ExecutionTime {
    private final List<Long> durations;  // tests results
    private final int PREINIT = 16;  // number of preinit tests
    private final int MEASURING = 128;  // number of tests in measuring

    /**
     * <p>Default constructor.</p>
     */
    public ExecutionTime() {
        this.durations = new ArrayList<>(this.MEASURING);
    }

    /**
     * <p>Test execution time of a given method.</p>
     *
     * @param task Task for testing.
     * @param <V></V> Task result type.
     * @return Execution time in milliseconds.
     */
    public <V> long measure(Callable<V> task) {
        /* Preparing for testing */
        this.durations.clear();
        for(int i = 0; i < this.PREINIT; i++) {
            try {
                task.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /* series of tests */
        for(int i = 0; i < this.MEASURING; i++) {
            this.durations.add(getDuration(task));
        }
        return this.durations.stream().filter(duration -> duration != 0).min(Long::compare).orElse((long) 0);
    }

    /* computes duration of method execution */
    private <V> long getDuration(Callable<V> task) {
        Instant start = Instant.now();
        try {
            task.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }
}
