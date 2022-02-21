package io.github.dtolmachev1.primes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Class for primality checking using multithreading.</p>
 */
public class ConcurrentPrimes extends Primes {
    private final int[] numbers;  // list of tasks for executing
    private int nThreads;  // number of threads in pool

    /**
     * <p>Default constructor.</p>
     *
     * @param numbers Array with numbers for checking.
     */
    public ConcurrentPrimes(int[] numbers) {
        this.numbers = numbers.clone();
        this.nThreads = 0;
    }

    /**
     * <p>Constructor that supports specifying number of threads.</p>
     *
     * @param numbers Array with numbers for checking.
     * @param nThreads Number of threads in the pool.
     */
    public ConcurrentPrimes(int[] numbers, int nThreads) throws IllegalArgumentException {
        this(numbers);
        setNThreads(nThreads);
    }

    /**
     * <p>Checks primality of all given numbers.</p>
     *
     * @return <code>true</code> if all numbers are prime or <code>false</code> otherwise.
     */
    @Override
    public boolean areAllPrimes() {
        ExecutorService executorService = newExecutorService();
        List<Future<Boolean>> results = Arrays.stream(this.numbers).mapToObj(number -> executorService.submit(() -> !isPrime(number))).collect(Collectors.toCollection(ArrayList::new));
        for(Future<Boolean> result : results) {
            try {
                if(result.get()) {
                    executorService.shutdownNow();
                    return false;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * <p>Returns current number of threads in the pool.</p>
     *
     * @return Current value of nThreads.
     */
    public int getNThreads() {
        return this.nThreads;
    }

    /**
     * <p>Sets number of threads in the pool to the specified value.</p>
     *
     * @param nThreads Number of threads in the pool.
     * @throws IllegalArgumentException If <code>nThreads <= 0</code>.
     */
    public void setNThreads(int nThreads) throws IllegalArgumentException {
        if(nThreads <= 0) {
            throw new IllegalArgumentException();
        }
        this.nThreads = nThreads;
    }

    /* creates ExecutorService with pool of nThreads */
    private ExecutorService newExecutorService() {
        if(this.nThreads == 1) {
            return Executors.newSingleThreadExecutor();
        }
        return Executors.newFixedThreadPool(this.nThreads != 0 ? this.nThreads : Runtime.getRuntime().availableProcessors());
    }
}