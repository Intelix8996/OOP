package ru.nsu.nrepin;

import java.util.Queue;

public class Baker implements Actor {

    private final int experience;

    private final Queue<Integer> orderQueue;
    private final Storage storage;

    public Baker(int experience, Queue<Integer> orderQueue, Storage storage) {
        this.experience = experience;
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
