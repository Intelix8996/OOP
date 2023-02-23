package ru.nsu.nrepin;

import java.util.List;

public class PrimeCheckerRunnerThread extends Thread {

    private final List<Integer> payload;
    private boolean result = false;

    public PrimeCheckerRunnerThread(List<Integer> payload) {
        this.payload = payload;
    }

    public boolean getResult() {
        return result;
    }

    @Override
    public void run() {
        SequentalPrimeChecker checker = new SequentalPrimeChecker();

        result = checker.checkList(payload);
    }
}
