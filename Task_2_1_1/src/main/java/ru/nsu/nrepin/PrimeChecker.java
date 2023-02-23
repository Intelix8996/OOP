package ru.nsu.nrepin;

import java.util.List;

/**
 * Abstract class for all checkers.
 */
public abstract class PrimeChecker {

    /**
     * Checks whether a list of integers has at least one composite number.
     *
     * @param numbers list of numbers to check
     * @return {@code true} if list contains composite number, {@code false} otherwise
     */
    public abstract boolean checkList(List<Integer> numbers);

    /**
     * Checks if number is prime.
     *
     * @param number number to check
     * @return {@code true} if number is prime, {@code false} otherwise
     */
    public static boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; ++i) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
