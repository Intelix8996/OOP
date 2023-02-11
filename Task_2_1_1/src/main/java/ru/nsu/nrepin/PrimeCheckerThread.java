package ru.nsu.nrepin;

import java.util.List;

public class PrimeCheckerThread extends Thread {

    private final List<Integer> payload;
    private boolean result = false;

    public PrimeCheckerThread(List<Integer> payload) {
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
