package ru.nsu.nrepin;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Class that creates different objects with parameters from json file.
 */
public class JsonObjectFactory {

    private final JsonSchema deserialized;

    /**
     * Creates new factory.
     *
     * @param filename json file to parse
     */
    public JsonObjectFactory(String filename) {
        String json = readFromFile(filename);

        deserialized = new Gson().fromJson(json, JsonSchema.class);
    }

    /**
     * Returns list of Baker objects with parameters specified in json file.
     *
     * @param orderQueue order queue that bakers will use
     * @param storage storage that bakers will use
     * @return list of Baker objects
     */
    public List<Baker> getBakers(Queue<Integer> orderQueue, Storage storage) {
        List<Baker> bakers = new ArrayList<>();

        int bakerNumber = 0;
        for (float bakerExperience : deserialized.bakers) {
            bakers.add(new Baker(bakerExperience, bakerNumber, orderQueue, storage));
            bakerNumber++;
        }

        return bakers;
    }

    /**
     * Returns list of Courier objects with parameters specified in json file.
     *
     * @param storage storage that couriers will use
     * @param orderRegistry registry that couriers will use
     * @return list of Courier objects
     */
    public List<Courier> getCouriers(Storage storage, OrderRegistry orderRegistry) {
        List<Courier> couriers = new ArrayList<>();

        int courierNumber = 0;
        for (int courierStorage : deserialized.couriers) {
            couriers.add(new Courier(courierStorage, courierNumber, storage, orderRegistry));
            courierNumber++;
        }

        return couriers;
    }

    /**
     * Returns Storage object with parameters specified in json file.
     *
     * @return Storage object
     */
    public Storage getStorage() {
        return new Storage(deserialized.storageCapacity);
    }

    /**
     * Returns OrderGenerator object with parameters specified in json file.
     *
     * @param orderQueue order queue that generator will use
     * @param registry registry that generator will use
     * @return OrderGenerator object
     */
    public OrderGenerator getOrderGenerator(Queue<Integer> orderQueue, OrderRegistry registry) {
        return new OrderGenerator(
                deserialized.orderGenerator.minTime,
                deserialized.orderGenerator.maxTime,
                deserialized.orderGenerator.orderCount,
                orderQueue,
                registry
        );
    }

    private String readFromFile(String filename) {
        InputStream inputStream = JsonObjectFactory.class.getResourceAsStream(filename);

        if (inputStream == null) {
            throw new Error("Can't open file");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder builder = new StringBuilder();

        while (true) {
            String line;

            try {
                line = reader.readLine();
            } catch (IOException e) {
                break;
            }

            if (line == null) {
                break;
            }

            builder.append(line);
        }

        return builder.toString();
    }
}