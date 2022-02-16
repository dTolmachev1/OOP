package io.github.dtolmachev1.primes;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.List;

/**
 * <p>Class for primality checking using multithreading.</p>
 */
public class ConcurrentPrimes extends Primes {
    private boolean result;  // for storing the result
    private final ExecutorService executorService;  // for thread pool
    private final List<Callable<Object>> tasks;  // list of tasks for executing
    private final int TIMEOUT = 60;  // termination timeout

    /**
     * <p>Default constructor.</p>
     *
     * @param numbers Array with numbers for checking.
     */
    public ConcurrentPrimes(int[] numbers) {
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.tasks = new ArrayList<>(numbers.length);
        this.result = true;
        for(Integer number : numbers) {
            tasks.add(Executors.callable(() -> runTask(number)));
        }
    }

    /**
     * <p>Checks primality of all given numbers.</p>
     *
     * @return <code>true</code> if all numbers are prime or <code>false</code> otherwise.
     */
    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public boolean areAllPrimes() {
        this.result = true;
        try {
            this.executorService.invokeAll(this.tasks);
            this.executorService.shutdown();
            this.executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch(RejectedExecutionException ignored) {}
        return this.result;
    }

    /* wrapper for single task runner */
    private void runTask(Integer number) {
        if(!isPrime(number)) {
            synchronized(this) {
                this.result = false;
            }
            this.executorService.shutdownNow();
        }
    }
}
