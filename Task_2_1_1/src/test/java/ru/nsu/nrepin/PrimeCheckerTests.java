package ru.nsu.nrepin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PrimeCheckerTests {

    private static final List<Integer> numbersFalse = List.of(1, 3, 5, 7, 11, 13, 17, 23, 31);
    private static final List<Integer> numbersTrue = List.of(1, 3, 5, 6, 7, 11);

    @Test
    void testIsPrime() {
        Assertions.assertTrue(PrimeChecker.isPrime(3));
        Assertions.assertFalse(PrimeChecker.isPrime(6));
    }

    @Test
    void testSequential() {
        PrimeChecker checker = new SequentalPrimeChecker();

        Assertions.assertFalse(checker.checkList(numbersFalse));

        Assertions.assertTrue(checker.checkList(numbersTrue));
    }

    @Test
    void testParallelStream() {
        PrimeChecker checker = new ParallelStreamPrimeChecker();

        Assertions.assertFalse(checker.checkList(numbersFalse));

        Assertions.assertTrue(checker.checkList(numbersTrue));
    }

    @Test
    void testSingleThreaded() {
        PrimeChecker checker = new ThreadedPrimeChecker(1);

        Assertions.assertFalse(checker.checkList(numbersFalse));

        Assertions.assertTrue(checker.checkList(numbersTrue));
    }

    @Test
    void testMultiThreaded() {
        int coreCount = Runtime.getRuntime().availableProcessors();

        PrimeChecker checker = new ThreadedPrimeChecker(coreCount);

        Assertions.assertFalse(checker.checkList(numbersFalse));

        Assertions.assertTrue(checker.checkList(numbersTrue));
    }
}
