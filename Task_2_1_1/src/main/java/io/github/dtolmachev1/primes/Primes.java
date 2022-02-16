package io.github.dtolmachev1.primes;

/**
 * <p>Interface for primality checking.</p>
 */
public abstract class Primes {
    /**
     * <p>Checks primality of all given numbers.</p>
     *
     * @return <code>true</code> if all numbers are prime or <code>false</code> otherwise.
     */
    public abstract boolean areAllPrimes();

    /**
     * <p>Checks primality of a given number.</p>
     *
     * @param number Number for checking.
     * @return <code>true</code> if given number is prime or <code>false</code> otherwise.
     */
    protected boolean isPrime(int number) {
        if(number == 1) {
            return false;
        }
        if(number == 2) {
            return true;
        }
        for(int i = 2; i * i <= number; i++) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
