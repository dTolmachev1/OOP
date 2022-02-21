package io.github.dtolmachev1.tests;

import io.github.dtolmachev1.primes.*;
import io.github.dtolmachev1.benchmark.ExecutionTime;

import java.util.Arrays;
import java.util.Random;

public class ConsoleApp {
    private static Random random;
    private static SequentialPrimes sequentialPrimes;  // for sequential execution
    private static StreamPrimes streamPrimes;  // for execution using parallel stream
    private static ConcurrentPrimes concurrentPrimes;  // for execution with multithreaded
    private static ExecutionTime executionTime;  // for time measuring
    private static final int PRIME = 1048571;  // prime number
    private static final int SEMIPRIME = 1048561;  // semiprime number
    private static final int N = 100000;  // numbers in the array

    public static void main(String[] args) {
        random = new Random();
        int nThreads = Runtime.getRuntime().availableProcessors();
        int[] numbers = new int[N];
        Arrays.fill(numbers, PRIME);
        numbers[random.nextInt(N - 1)] = SEMIPRIME;
        sequentialPrimes = new SequentialPrimes(numbers);
        streamPrimes = new StreamPrimes(numbers);
        concurrentPrimes = new ConcurrentPrimes(numbers);
        executionTime = new ExecutionTime();
        System.out.println("Sequential execution: " + executionTime.measure(sequentialPrimes::areAllPrimes) + ".");
        System.out.println("Execution using parallel stream: " + executionTime.measure(streamPrimes::areAllPrimes) + ".");
        for(int i = 1; i <= 2 * nThreads; i++) {
            concurrentPrimes.setNThreads(i);
            System.out.println("Execution with " + i + " threads: " + executionTime.measure(concurrentPrimes::areAllPrimes) + ".");
        }
    }
}
