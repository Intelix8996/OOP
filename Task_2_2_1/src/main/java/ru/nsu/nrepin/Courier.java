package ru.nsu.nrepin;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents courier.
 */
public class Courier extends StoppableThread {

    private static final int DELIVERY_DURATION = 3500;

    private final int storageSize;

    private final Storage storage;
    private final OrderRegistry orderRegistry;

    private boolean shouldStop = false;

    private final int courierNumber;

    /**
     * Creates new courier with given parameters.
     *
     * @param storageSize size of courier's storage
     * @param number courier's id
     * @param storage storage that courier will use
     * @param orderRegistry registry that courier will use
     */
    public Courier(int storageSize, int number, Storage storage, OrderRegistry orderRegistry) {
        this.storageSize = storageSize;
        this.storage = storage;
        this.orderRegistry = orderRegistry;
        this.courierNumber = number;
    }

    @Override
    public void run() {
        while (!shouldStop) {
            List<Integer> ids = new ArrayList<>();
            boolean takeSuccessful = false;

            while (!takeSuccessful) {
                if (shouldStop) {
                    return;
                }

                synchronized (storage) {
                    if (!storage.isEmpty()) {
                        for (int i = 0; i < storageSize; ++i) {
                            int id = storage.take();
                            ids.add(id);
                            takeSuccessful = true;
                            System.out.printf(
                                    "[Courier-%d] Take from queue %d%n",
                                    courierNumber,
                                    id
                            );

                            if (storage.isEmpty()) {
                                break;
                            }
                        }
                    }
                }
            }

            try {
                Thread.sleep((long) DELIVERY_DURATION * ids.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (orderRegistry) {
                for (int id : ids) {
                    orderRegistry.markFinished(id);
                    System.out.printf("[Courier-%d] Finish delivery %d%n", courierNumber, id);
                }
            }
        }
    }

    @Override
    void requestStop() {
        shouldStop = true;
    }
}
