package ru.nsu.nrepin;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        CommandLine cmd = ArgumentParser.parseArguments(args);

        Notebook notebook = NotebookWriter.restore();

        if (cmd.hasOption("add")) {
            String[] arguments = cmd.getOptionValues("add");

            notebook.add(arguments[0], arguments[1]);
        }

        if (cmd.hasOption("rm")) {
            notebook.remove(cmd.getOptionValue("rm"));
        }

        if (cmd.hasOption("show")) {
            System.out.println(notebook);
        }

        NotebookWriter.save(notebook);
    }
}