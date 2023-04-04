package ru.nsu.nrepin;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to keep track of orders.
 */
public class OrderRegistry {

    private final Map<Integer, Boolean> registry;

    /**
     * Creates new order registry.
     */
    public OrderRegistry() {
        registry = new HashMap<>();
    }

    /**
     * Adds new order to registry.
     *
     * @param id order id to add
     */
    public synchronized void add(int id) {
        registry.put(id, false);
    }

    /**
     * Marks order as finished.
     *
     * @param id order id to mark
     */
    public synchronized void markFinished(int id) {
        registry.put(id, true);
    }

    /**
     * Checks if all orders are finished.
     *
     * @return status of all orders as {@code boolean}
     */
    public boolean allFinished() {
        for (Integer key : registry.keySet()) {
            if (!registry.get(key)) {
                return false;
            }
        }

        return true;
    }
}
