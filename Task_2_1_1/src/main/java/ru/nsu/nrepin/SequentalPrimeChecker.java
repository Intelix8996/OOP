package ru.nsu.nrepin;

import java.util.List;

public class SequentalPrimeChecker extends PrimeChecker {
    @Override
    public boolean checkList(List<Integer> numbers) {
        for (int number : numbers) {
            if (!isPrime(number)) {
                return true;
            }
        }

        return false;
    }
}
