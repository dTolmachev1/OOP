package io.github.dtolmachev1.tests;

import io.github.dtolmachev1.primes.*;
import io.github.dtolmachev1.benchmark.ExecutionTime;

import java.util.Arrays;
import java.util.Random;

public class ConsoleApp {
    public static void main(String[] args) {
        random = new Random();
        int[] numbers = new int[N];
        Arrays.fill(numbers, 1000007);
        numbers[Random.nextInt(N - 1)] = 1014049;
        sequentialPrimes = new SequentialPrimes(numbers);
        streamPrimes = new StreamPrimes(numbers);
        concurrentPrimes = new ConcurrentPrimes(numbers);
        executionTime = new ExecutionTime();
        System.out.println("Sequential execution: " + executionTime.measure(sequentialPrimes::areAllPrimes) + ".");
        System.out.println("Execution using parallel stream: " + executionTime.measure(streamPrimes::areAllPrimes) + ".");
        System.out.println("Execution with multithreaded: " + executionTime.measure(concurrentPrimes::areAllPrimes) + ".");
    }

    private static Random random;
    private static Primes sequentialPrimes;  // for sequential execution
    private static Primes streamPrimes;  // for execution using parallel stream
    private static Primes concurrentPrimes;  // for execution with multithreaded
    private static ExecutionTime executionTime;  // for time measuring
    private static final int N = 10000000;  // numbers in the array
}
