package ru.nsu.nrepin;

import java.util.ArrayList;
import java.util.List;

public class ThreadPrimeChecker extends PrimeChecker {

    private final int threadCount;

    public ThreadPrimeChecker(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public boolean checkList(List<Integer> numbers) {
        int payloadSize = (numbers.size() + 1) / threadCount;

        List<PrimeCheckerThread> workers = new ArrayList<>();

        for (int i = 0; i < threadCount; ++i) {

            List<Integer> payload;

            if (i == threadCount - 1) {
                payload = numbers.subList(i * payloadSize, numbers.size());
            } else {
                payload = numbers.subList(i * payloadSize, i * payloadSize + payloadSize);
            }

            PrimeCheckerThread worker = new PrimeCheckerThread(payload);
            worker.start();
            workers.add(worker);
        }

        boolean result = false;

        for (PrimeCheckerThread worker : workers) {

            try {
                worker.join();
            } catch (InterruptedException e) {
                throw new InternalError("Error in " + worker.getName());
            }

            if (worker.getResult()) {
                result = true;
            }
        }

        return result;
    }
}
