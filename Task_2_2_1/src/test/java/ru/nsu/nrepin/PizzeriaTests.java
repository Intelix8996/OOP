package ru.nsu.nrepin;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for pizzeria classes.
 */
public class PizzeriaTests {

    @Test
    public void testStorage() {
        Storage storage = new Storage(2);

        int id = 1234;

        Assertions.assertTrue(storage.isEmpty());

        try {
            storage.store(id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int id2 = 3456;

        try {
            storage.store(id2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertFalse(storage.isEmpty());

        try {
            Assertions.assertEquals(storage.take(), id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
        final int orderCount = 3;

        BlockingQueue<Integer> orderQueue = new ArrayBlockingQueue<>(orderCount);
        OrderRegistry orderRegistry = new OrderRegistry();

        OrderGenerator orderGenerator = new OrderGenerator(
                99,
                100,
                orderCount,
                orderQueue,
                orderRegistry
        );

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
        BlockingQueue<Integer> orderQueue = new ArrayBlockingQueue<>(5);
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

        try {
            Assertions.assertEquals(1, storage.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCourier() {
        OrderRegistry orderRegistry = new OrderRegistry();
        Storage storage = new Storage(5);
        try {
            storage.store(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        orderRegistry.add(1);

        Assertions.assertFalse(orderRegistry.allFinished());
        Assertions.assertFalse(storage.isEmpty());

        Courier courier = new Courier(1, 0, storage, orderRegistry);

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

        BlockingQueue<Integer> orderQueue = new ArrayBlockingQueue<>(factory.getOrderCount());
        OrderRegistry orderRegistry = new OrderRegistry();

        Storage storage = factory.getStorage();
        List<Baker> bakers = factory.getBakers(orderQueue, storage);
        List<Courier> couriers = factory.getCouriers(storage, orderRegistry);

        Assertions.assertEquals(3, bakers.size());
        Assertions.assertEquals(2, couriers.size());
        Assertions.assertEquals(15, factory.getOrderCount());
    }
}
