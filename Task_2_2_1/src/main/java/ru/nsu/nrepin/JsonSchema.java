package ru.nsu.nrepin;

import java.util.List;

/**
 * Schema for JSON file.
 */
public class JsonSchema {

    public int storageCapacity;
    public List<Float> bakers;
    public List<Integer> couriers;
    public OrderGeneratorJsonSchema orderGenerator;

    public static class OrderGeneratorJsonSchema {
        public int minTime;
        public int maxTime;
        public int orderCount;
    }

}
