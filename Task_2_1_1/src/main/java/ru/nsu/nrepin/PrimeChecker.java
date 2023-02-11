package ru.nsu.nrepin;

import java.util.List;

public abstract class PrimeChecker {
    public abstract boolean checkList(List<Integer> numbers);

    public static boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; ++i) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
