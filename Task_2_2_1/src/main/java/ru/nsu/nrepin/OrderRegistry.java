package ru.nsu.nrepin;

import java.util.HashMap;
import java.util.Map;

public class OrderRegistry {

    private final Map<Integer, Boolean> registry;

    public OrderRegistry() {
        registry = new HashMap<>();
    }

    public void add(int id) {
        registry.put(id, false);
    }

    public void markFinished(int id) {
        registry.put(id, true);
    }

    public boolean allFinished() {
        for (Integer key : registry.keySet()) {
            if (!registry.get(key)) {
                return false;
            }
        }

        return true;
    }
}
