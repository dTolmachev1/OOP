package io.github.dtolmachev1;

import io.github.dtolmachev1.primes.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

class PrimesTest {
    @BeforeAll
    static void setUp() {
        random = new Random();
    }

    @Test
    @DisplayName("Example 1, sequential")
    void isAllPrimes_Sequential1() {
        Primes primes = new SequentialPrimes(new int[]{6, 8, 7, 13, 9, 4});
        Assertions.assertFalse(primes.areAllPrimes());
    }

    @Test
    @DisplayName("Example 1, using parallel stream")
    void isAllPrimes_Stream1() {
        Primes primes = new StreamPrimes(new int[]{6, 8, 7, 13, 9, 4});
        Assertions.assertFalse(primes.areAllPrimes());
    }

    @Test
    @DisplayName("Example 1, with multithreading")
    void isAllPrimes_Concurrent1() {
        Primes primes = new ConcurrentPrimes(new int[]{6, 8, 7, 13, 9, 4});
        Assertions.assertFalse(primes.areAllPrimes());
    }

    @Test
    @DisplayName("Example 2, sequential")
    void isAllPrimes_Sequential2() {
        Primes primes = new SequentialPrimes(new int[]{6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053});
        Assertions.assertTrue(primes.areAllPrimes());
    }

    @Test
    @DisplayName("Example 2, using parallel stream")
    void isAllPrimes_Stream2() {
        Primes primes = new StreamPrimes(new int[]{6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053});
        Assertions.assertTrue(primes.areAllPrimes());
    }

    @Test
    @DisplayName("Example 2, with multithreading")
    void isAllPrimes_Concurrent2() {
        Primes primes = new ConcurrentPrimes(new int[]{6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053});
        Assertions.assertTrue(primes.areAllPrimes());
    }

    @Test
    @DisplayName("Random numbers, using parallel stream")
    void isAllPrimes_Stream_Random() {
        int[] numbers = new int[N];
        Arrays.setAll(numbers, i -> random.nextInt(Integer.MAX_VALUE) + 1);
        Primes sequentialPrimes = new SequentialPrimes(numbers);
        Primes streamPrimes = new StreamPrimes(numbers);
        Assertions.assertEquals(sequentialPrimes.areAllPrimes(), streamPrimes.areAllPrimes());
    }

    @Test
    @DisplayName("Random numbers, with multithreading")
    void isAllPrimes_Concurrent_Random() {
        int[] numbers = new int[N];
        Arrays.setAll(numbers, i -> random.nextInt(Integer.MAX_VALUE) + 1);
        Primes sequentialPrimes = new SequentialPrimes(numbers);
        Primes concurrentPrimes = new ConcurrentPrimes(numbers);
        Assertions.assertEquals(sequentialPrimes.areAllPrimes(), concurrentPrimes.areAllPrimes());
    }

    private static Random random;
    private static final int N = 10000000;  // numbers in the array
}
