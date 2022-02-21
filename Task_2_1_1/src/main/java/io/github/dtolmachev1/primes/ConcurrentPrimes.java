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

    /**
     * <p>Default constructor.</p>
     *
     * @param numbers Array with numbers for checking.
     */
    public ConcurrentPrimes(int[] numbers) {
        this.numbers = numbers.clone();
    }

    /**
     * <p>Checks primality of all given numbers.</p>
     *
     * @return <code>true</code> if all numbers are prime or <code>false</code> otherwise.
     */
    @Override
    public boolean areAllPrimes() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
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
}