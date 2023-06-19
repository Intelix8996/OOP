package ru.nsu.nrepin.pizzeria;

import java.util.List;
import ru.nsu.nrepin.serde.JsonObjectFactory;
import ru.nsu.nrepin.util.BlockingQueue;

/**
 * Pizzeria class.
 *
 * <p>Runs the simulation.</p>
 */
public class Pizzeria {

    /**
     * Runs the simulation with parameters from specified config file.
     *
     * @param config config file
     */
    public static void runSimulation(String config) {

        JsonObjectFactory factory = new JsonObjectFactory(config);

        BlockingQueue<Integer> orderQueue = new BlockingQueue<>(factory.getOrderCount());
        OrderRegistry orderRegistry = new OrderRegistry();

        Storage storage = factory.getStorage();

        OrderGenerator orderGenerator = factory.getOrderGenerator(orderQueue, orderRegistry);
        List<Baker> bakers = factory.getBakers(orderQueue, storage);
        List<Courier> couriers = factory.getCouriers(storage, orderRegistry);

        orderGenerator.start();

        for (Baker baker : bakers) {
            baker.start();
        }

        for (Courier courier : couriers) {
            courier.start();
        }

        while (!orderGenerator.finished() || !orderRegistry.allFinished()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (Courier courier : couriers) {
            courier.requestStop();

            try {
                courier.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (Baker baker : bakers) {
            baker.requestStop();

            try {
                baker.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        orderGenerator.requestStop();
        try {
            orderGenerator.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}