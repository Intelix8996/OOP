package ru.nsu.nrepin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PrimeCheckerTests {

    @Test
    void testIsPrime() {
        Assertions.assertTrue(PrimeChecker.isPrime(3));
        Assertions.assertFalse(PrimeChecker.isPrime(6));
    }

    @Test
    void testSequential() {
        List<Integer> numbers = List.of(1, 3, 5, 7, 11, 13, 17, 23, 31);

        PrimeChecker checker = new SequentalPrimeChecker();

        Assertions.assertFalse(checker.checkList(numbers));

        numbers = List.of(1, 3, 5, 6, 7, 11);

        Assertions.assertTrue(checker.checkList(numbers));
    }
}
