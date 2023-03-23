package ru.nsu.nrepin;

import java.util.Stack;

/**
 * Class that represents Storage.
 */
public class Storage {

    private final Stack<Integer> storage;

    private final int capacity;

    /**
     * Creates new storage with given capacity.
     *
     * @param capacity storage capacity
     */
    public Storage(int capacity) {
        this.capacity = capacity;
        storage = new Stack<>();
    }

    /**
     * Puts order in storage.
     *
     * @param id order id to store
     */
    public void store(int id) {
        if (storage.size() == capacity) {
            return;
        }

        storage.push(id);
    }

    /**
     * Checks if new order can be stored.
     *
     * @return {@code true} if order can be stored, {@code false} otherwise
     */
    public boolean canStore() {
        return storage.size() < capacity;
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
    public int take() {
        return storage.pop();
    }
}
