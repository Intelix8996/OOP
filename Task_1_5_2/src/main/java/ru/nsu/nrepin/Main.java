package ru.nsu.nrepin;

import org.apache.commons.cli.CommandLine;

/**
 * Application main class.
 */
public class Main {

    /**
     * Application entry point.
     *
     * @param args application arguments
     */
    public static void main(String[] args) {

        CommandLine cmd = ArgumentParser.parseArguments(args);

        Notebook notebook = NotebookWriter.restore();

        ArgumentParser.executeCommands(cmd, notebook);

        NotebookWriter.save(notebook);
    }
}