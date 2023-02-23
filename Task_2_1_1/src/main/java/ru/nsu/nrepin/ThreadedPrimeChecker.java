package ru.nsu.nrepin;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a checker that uses threads to check a list of numbers.
 */
public class ThreadedPrimeChecker extends PrimeChecker {

    private final int threadCount;

    /**
     * Creates new checker with given count of threads.
     *
     * @param threadCount count of threads
     */
    public ThreadedPrimeChecker(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public boolean checkList(List<Integer> numbers) {
        int payloadSize = (numbers.size() + 1) / threadCount;

        List<PrimeCheckerRunnerThread> workers = new ArrayList<>();

        for (int i = 0; i < threadCount; ++i) {

            List<Integer> payload;

            if (i == threadCount - 1) {
                payload = numbers.subList(i * payloadSize, numbers.size());
            } else {
                payload = numbers.subList(i * payloadSize, i * payloadSize + payloadSize);
            }

            PrimeCheckerRunnerThread worker = new PrimeCheckerRunnerThread(payload);
            worker.start();
            workers.add(worker);
        }

        boolean result = false;

        for (PrimeCheckerRunnerThread worker : workers) {

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
