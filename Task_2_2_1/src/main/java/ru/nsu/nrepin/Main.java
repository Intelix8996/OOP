package ru.nsu.nrepin;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Main class.
 */
public class Main {

    private final static int STORAGE_CAPACITY = 15;
    private final static int BAKERS_COUNT = 15;
    private final static int INITIAL_ORDERS_COUNT = 15;
    private final static int BAKERS_EXPERIENCE = 15;
    private final static int COURIER_STORAGE_SIZE = 15;
    private final static int COURIERS_COUNT = 15;

    public static void main(String[] args) {

        Queue<Integer> orderQueue = new ArrayDeque<>();

        Storage storage = new Storage(STORAGE_CAPACITY);

        List<Baker> bakers = new ArrayList<>();

        List<Courier> couriers = new ArrayList<>();

        for (int i = 0; i < BAKERS_COUNT; ++i) {
            bakers.add(new Baker(BAKERS_EXPERIENCE, orderQueue, storage));
        }

        for (int i = 0; i < COURIERS_COUNT; ++i) {
            couriers.add(new Courier(COURIER_STORAGE_SIZE, orderQueue, storage));
        }

        Random random = new Random();

        for (int i = 0; i < INITIAL_ORDERS_COUNT; ++i) {
            orderQueue.add(random.nextInt());
        }

        // Run in threads
        for (Baker baker : bakers) {
            baker.start();
        }

        for (Courier courier : couriers) {
            courier.start();
        }
    }
}