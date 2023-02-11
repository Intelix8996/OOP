package ru.nsu.nrepin;

import java.util.List;

public class ParallelStreamPrimeChecker extends PrimeChecker {
    @Override
    public boolean checkList(List<Integer> numbers) {
        return numbers.parallelStream().anyMatch((number) -> !isPrime(number));
    }
}
