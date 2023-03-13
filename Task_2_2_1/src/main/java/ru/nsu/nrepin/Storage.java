package ru.nsu.nrepin;

import java.util.Stack;

public class Storage {

    private final Stack<Integer> storage;

    private final int capacity;

    public Storage(int capacity) {
        this.capacity = capacity;
        storage = new Stack<>();
    }

    public void store(int id) {
        if (storage.size() == capacity) {
            return;
        }

        storage.push(id);
    }

    public boolean canStore() {
        return storage.size() < capacity;
    }

    public int take() {
        return storage.pop();
    }
}
