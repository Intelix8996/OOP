package ru.nsu.nrepin;

import java.util.List;

/**
 * A thread class that is dispatched by ThreadedPrimeChecker.
 */
public class PrimeCheckerRunnerThread extends Thread {

    private final List<Integer> payload;
    private boolean result = false;

    /**
     * Create new thread with given payload as list of integers.
     *
     * @param payload list of nuumbers to check
     */
    public PrimeCheckerRunnerThread(List<Integer> payload) {
        this.payload = payload;
    }

    /**
     * Returns result of computation. Should be called only after computation is done.
     *
     * @return {@code true} if list contains composite number, {@code false} otherwise
     */
    public boolean getResult() {
        return result;
    }

    @Override
    public void run() {
        SequentalPrimeChecker checker = new SequentalPrimeChecker();

        result = checker.checkList(payload);
    }
}
