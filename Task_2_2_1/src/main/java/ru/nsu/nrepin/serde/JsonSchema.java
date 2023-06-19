package ru.nsu.nrepin.serde;

import java.util.List;

/**
 * Schema for JSON file.
 */
public class JsonSchema {

    public int storageCapacity;
    public List<Float> bakers;
    public List<Integer> couriers;
    public OrderGeneratorJsonSchema orderGenerator;

    /**
     * Schema for orderGenerator section.
     */
    public static class OrderGeneratorJsonSchema {
        public int minTime;
        public int maxTime;
        public int orderCount;
    }

}
