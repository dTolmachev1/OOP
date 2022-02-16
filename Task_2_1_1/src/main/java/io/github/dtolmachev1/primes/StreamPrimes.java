package io.github.dtolmachev1.primes;

import java.util.Arrays;

/**
 * <p>Class for primality checking using ParallelStream API.</p>
 */
public class StreamPrimes extends Primes {
    private final int[] numbers;

    /**
     * <p>Default constructor.</p>
     *
     * @param numbers Array with numbers for checking.
     */
    public StreamPrimes(int[] numbers) {
    this.numbers = numbers.clone();
    }

    /**
     * <p>Checks primality of all given numbers.</p>
     *
     * @return <code>true</code> if all numbers are prime or <code>false</code> otherwise.
     */
    @Override
    @SuppressWarnings("SimplifyStreamApiCallChains")
    public boolean areAllPrimes() {
        return !Arrays.stream(this.numbers).parallel().anyMatch(number -> !isPrime(number));
    }
}
