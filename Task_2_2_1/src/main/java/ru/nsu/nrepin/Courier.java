package ru.nsu.nrepin;

import java.util.Queue;

public class Courier implements Actor {

    private final int storageSize;

    private final Queue<Integer> orderQueue;
    private final Storage storage;

    public Courier(int storageSize, Queue<Integer> orderQueue, Storage storage) {
        this.storageSize = storageSize;
        this.orderQueue = orderQueue;
        this.storage = storage;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
