package ru.nsu.nrepin.pizzeria;

import ru.nsu.nrepin.util.BlockingQueue;
import ru.nsu.nrepin.util.StoppableThread;

import java.util.Random;

/**
 * This class is used to generate orders.
 *
 * <p>It adds them to order queue and order registry.</p>
 */
public class OrderGenerator extends StoppableThread {

    private final int minTime;
    private final int maxTime;

    private final int ordersCount;
    private int ordersGenerated;

    private boolean shouldStop = false;

    private final BlockingQueue<Integer> orderQueue;
    private final OrderRegistry orderRegistry;

    /**
     * Creates new order generator.
     *
     * @param min minimal time between orders
     * @param max maximum time between orders
     * @param count order count
     * @param queue queue to use
     * @param registry registry to use
     */
    public OrderGenerator(int min, int max, int count,
                          BlockingQueue<Integer> queue, OrderRegistry registry) {
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
                return;
            }

            int id = nextId++;

            try {
                orderQueue.put(id);
            } catch (InterruptedException e) {
                return;
            }
            orderRegistry.add(id);

            //System.out.println("[Generator] Add to queue " + id);

            ordersGenerated++;

            if (ordersGenerated == ordersCount) {
                requestStop();
            }
        }
    }

    /**
     * Checks if generator generated all orders.
     *
     * @return status of generator as {@code boolean}
     */
    public boolean finished() {
        return ordersGenerated == ordersCount;
    }

    @Override
    public void requestStop() {
        shouldStop = true;
        interrupt();
    }
}
