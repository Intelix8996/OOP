package ru.nsu.nrepin;

import java.util.Queue;
import java.util.Random;

public class OrderGenerator extends StoppableThread {

    private final int minTime;
    private final int maxTime;

    private final int ordersCount;
    private int ordersGenerated;

    private boolean shouldStop = false;

    private final Queue<Integer> orderQueue;
    private final OrderRegistry orderRegistry;

    public OrderGenerator(int min, int max, int count,
                          Queue<Integer> queue, OrderRegistry registry) {
        minTime = min;
        maxTime = max;
        orderQueue = queue;
        orderRegistry = registry;
        ordersCount = count;
        ordersGenerated = 0;
    }

    @Override
    public void run() {
        int nextId = 0;
        Random random = new Random();

        while (!shouldStop) {
            try {
                Thread.sleep(random.nextInt(maxTime - minTime) + minTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int id = nextId++;

            synchronized(orderQueue) {
                orderQueue.add(id);
            }

            synchronized (orderRegistry) {
                orderRegistry.add(id);
            }

            //System.out.println("[Generator] Add to queue " + id);

            ordersGenerated++;

            if (ordersGenerated == ordersCount) {
                requestStop();
            }
        }
    }

    public boolean finished() {
        return ordersGenerated == ordersCount;
    }

    @Override
    void requestStop() {
        shouldStop = true;
    }
}
