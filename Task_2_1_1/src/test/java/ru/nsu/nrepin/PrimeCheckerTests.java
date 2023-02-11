package ru.nsu.nrepin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeCheckerTests {

    @Test
    void testIsPrime() {
        Assertions.assertTrue(PrimeChecker.isPrime(3));
        Assertions.assertFalse(PrimeChecker.isPrime(6));
    }
}
