package ru.nsu.nrepin;

import org.apache.commons.cli.CommandLine;

public class Main {
    public static void main(String[] args) {

        CommandLine cmd = ArgumentParser.parseArguments(args);

        Notebook notebook = NotebookWriter.restore();

        ArgumentParser.executeCommands(cmd, notebook);

        NotebookWriter.save(notebook);
    }
}