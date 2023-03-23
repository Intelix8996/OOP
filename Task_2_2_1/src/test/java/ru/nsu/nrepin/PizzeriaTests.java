package ru.nsu.nrepin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Tests for pizzeria classes.
 */
public class PizzeriaTests {

    @Test
    public void testStorage() {
        Storage storage = new Storage(1);

        int id = 1234;

        Assertions.assertTrue(storage.isEmpty());

        Assertions.assertTrue(storage.canStore());

        storage.store(id);

        int id2 = 3456;

        storage.store(id2);

        Assertions.assertFalse(storage.isEmpty());

        Assertions.assertFalse(storage.canStore());

        Assertions.assertEquals(storage.take(), id);
    }

    @Test
    public void testOrderRegistry() {
        OrderRegistry orderRegistry = new OrderRegistry();

        orderRegistry.add(1234);

        Assertions.assertFalse(orderRegistry.allFinished());

        orderRegistry.markFinished(1234);

        Assertions.assertTrue(orderRegistry.allFinished());
    }

    @Test
    public void testOrderGenerator() {
        Queue<Integer> orderQueue = new ArrayDeque<>();
        OrderRegistry orderRegistry = new OrderRegistry();

        OrderGenerator orderGenerator = new OrderGenerator(99, 100, 3, orderQueue, orderRegistry);

        Assertions.assertFalse(orderGenerator.finished());

        orderGenerator.start();

        try {
            orderGenerator.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(orderGenerator.finished());

        Assertions.assertEquals(3, orderQueue.size());
        Assertions.assertFalse(orderRegistry.allFinished());

        for (int i = 0; i < 3; ++i) {
            orderRegistry.markFinished(i);
        }

        Assertions.assertTrue(orderRegistry.allFinished());
    }

    @Test
    public void testBaker() {
        Queue<Integer> orderQueue = new ArrayDeque<>();
        Storage storage = new Storage(5);

        Baker baker = new Baker(1, 0, orderQueue, storage);

        orderQueue.add(1);

        Assertions.assertTrue(storage.isEmpty());

        baker.start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        baker.requestStop();
        try {
            baker.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(1, storage.take());
    }

    @Test
    public void testCourier() {
        OrderRegistry orderRegistry = new OrderRegistry();
        Storage storage = new Storage(5);

        Courier courier = new Courier(1, 0, storage, orderRegistry);

        storage.store(1);
        orderRegistry.add(1);

        Assertions.assertFalse(orderRegistry.allFinished());
        Assertions.assertFalse(storage.isEmpty());

        courier.start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        courier.requestStop();
        try {
            courier.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(orderRegistry.allFinished());
        Assertions.assertTrue(storage.isEmpty());
    }

    @Test
    public void testJsonFactory() {
        JsonObjectFactory factory = new JsonObjectFactory("/config.json");

        Queue<Integer> orderQueue = new ArrayDeque<>();
        OrderRegistry orderRegistry = new OrderRegistry();

        Storage storage = factory.getStorage();
        List<Baker> bakers = factory.getBakers(orderQueue, storage);
        List<Courier> couriers = factory.getCouriers(storage, orderRegistry);
        OrderGenerator orderGenerator = factory.getOrderGenerator(orderQueue, orderRegistry);

        Assertions.assertEquals(3, bakers.size());
        Assertions.assertEquals(2, couriers.size());
    }
}
