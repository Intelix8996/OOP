package ru.nsu.nrepin;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ArgumentParser {
    public static CommandLine parseArguments(String[] args) {

        final Options options = new Options();

        Option addOption = Option.builder()
                .option("add")
                .desc("Create new note")
                .hasArgs()
                .numberOfArgs(2)
                .type(String.class)
                .build();

        Option removeOption = Option.builder()
                .option("rm")
                .desc("Remove note")
                .hasArg()
                .numberOfArgs(1)
                .type(String.class)
                .build();

        Option showOption = Option.builder()
                .option("show")
                .desc("Show notes")
                .build();

        options.addOption(addOption);
        options.addOption(removeOption);
        options.addOption(showOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException exp){
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            throw new IllegalStateException(exp.getMessage());
        }

        return cmd;
    }
}
