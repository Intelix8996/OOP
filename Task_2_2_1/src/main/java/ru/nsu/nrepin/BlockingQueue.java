package ru.nsu.nrepin;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Implementation of thread-safe queue.
 *
 * @param <T> type of elements in queue
 */
public class BlockingQueue<T> {

    private final Queue<T> queue = new ArrayDeque<>();

    private final int capacity;

    /**
     * Creates new queue with given capacity.
     *
     * @param capacity capacity of queue
     */
    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Takes value from the end of queue.
     *
     * @return value from the end of the queue
     * @throws InterruptedException if exception occurs during blocking time
     */
    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }

        T returnValue = queue.poll();
        notifyAll();
        return returnValue;
    }

    /**
     * Adds value to the beginning of the queue.
     *
     * @param val value to add
     * @throws InterruptedException if exception occurs during blocking time
     */
    public synchronized void put(T val) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }

        queue.add(val);
        notifyAll();
    }

    /**
     * Returns current number of elements in queue.
     *
     * @return number of elements in queue
     */
    public int size() {
        return queue.size();
    }
}
