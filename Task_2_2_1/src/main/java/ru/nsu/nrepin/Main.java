package ru.nsu.nrepin;

import ru.nsu.nrepin.pizzeria.Pizzeria;

/**
 * Program Main class.
 */
public class Main {

    private static final String CONFIG_FILE = "/config.json";

    /**
     * Program entry point.
     *
     * @param args program args
     */
    public static void main(String[] args) {
        Pizzeria.runSimulation(CONFIG_FILE);
    }
}
