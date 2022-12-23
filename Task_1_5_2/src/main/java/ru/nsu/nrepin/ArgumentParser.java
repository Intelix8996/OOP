package ru.nsu.nrepin;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class handles arguments passed to application.
 */
public class ArgumentParser {

    private static final DateTimeFormatter formatter = Notebook.getDateTimeFormatter();

    private static final Option ADD_OPTION;
    private static final Option REMOVE_OPTION;
    private static final Option SHOW_OPTION;

    private static final Options OPTIONS;

    static {
        ADD_OPTION = Option.builder()
                .option("add")
                .desc("Create new note")
                .hasArgs()
                .numberOfArgs(2)
                .type(String.class)
                .build();

        REMOVE_OPTION = Option.builder()
                .option("rm")
                .desc("Remove note")
                .hasArg()
                .numberOfArgs(1)
                .type(String.class)
                .build();

        SHOW_OPTION = Option.builder()
                .option("show")
                .desc("Show notes")
                .hasArgs()
                .numberOfArgs(3)
                .type(String.class)
                .optionalArg(true)
                .build();

        OPTIONS = new Options();

        OPTIONS.addOption(ADD_OPTION);
        OPTIONS.addOption(REMOVE_OPTION);
        OPTIONS.addOption(SHOW_OPTION);
    }

    /**
     * Parses given array of arguments into {@code CommandLine} object.
     *
     * @param args arguments to parse
     * @return parsed arguments as {@code CommandLine} object
     */
    public static CommandLine parseArguments(String[] args) {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(OPTIONS, args);
        } catch (ParseException exp){
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            throw new IllegalStateException(exp.getMessage());
        }

        return cmd;
    }

    /**
     * Executes commands parsed from arguments.
     *
     * @param cmd parsed commands as {@code CommandLine} object
     * @param notebook {@code Notebook} object to execute commands on
     */
    public static void executeCommands(CommandLine cmd, Notebook notebook) {
        if (cmd.hasOption(ADD_OPTION)) {
            String[] arguments = cmd.getOptionValues(ADD_OPTION);

            notebook.add(arguments[0], arguments[1]);
        }

        if (cmd.hasOption(REMOVE_OPTION)) {
            notebook.remove(cmd.getOptionValue(REMOVE_OPTION));
        }

        if (cmd.hasOption(SHOW_OPTION)) {
            String[] arguments = cmd.getOptionValues(SHOW_OPTION);

            if (arguments == null) {
                System.out.println(notebook.toSortedString());
            } else {

                List<String> keywords =
                        new ArrayList<>(Arrays.asList(arguments).subList(2, arguments.length));

                System.out.println(notebook.toSortedFilteredString(
                        LocalDateTime.parse(arguments[0], formatter),
                        LocalDateTime.parse(arguments[1], formatter),
                        keywords
                ));
            }
        }
    }
}
