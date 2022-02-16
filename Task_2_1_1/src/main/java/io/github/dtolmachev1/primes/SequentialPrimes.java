package io.github.dtolmachev1.primes;

/**
 * <p>Class for primality checking without using multithreading.</p>
 */
public class SequentialPrimes extends Primes {
    private final int[] numbers;  // for storing numbers

    /**
     * <p>Default constructor.</p>
     *
     * @param numbers Array with numbers for checking.
     */
    public SequentialPrimes(int[] numbers) {
        this.numbers = numbers.clone();
    }

    /**
     * <p>Checks primality of all given numbers.</p>
     *
     * @return <code>true</code> if all numbers are prime or <code>false</code> otherwise.
     */
    @Override
    public boolean areAllPrimes() {
        for(int number : this.numbers) {
            if(!isPrime(number)) {
                return false;
            }
        }
        return true;
    }
}
