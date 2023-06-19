package ru.nsu.nrepin.pizzeria;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class that represents Storage.
 */
public class Storage {

    private final BlockingQueue<Integer> storage;

    /**
     * Creates new storage with given capacity.
     *
     * @param capacity storage capacity
     */
    public Storage(int capacity) {
        storage = new ArrayBlockingQueue<>(capacity);
    }

    /**
     * Puts order in storage.
     *
     * @param id order id to store
     */
    public void store(int id) throws InterruptedException {
        storage.put(id);
    }

    /**
     * Checks if storage is empty.
     *
     * @return {@code true} if storage is empty, {@code false} otherwise
     */
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    /**
     * Takes last order from storage.
     *
     * @return order form storage
     */
    public int take() throws InterruptedException {
        return storage.take();
    }
}
