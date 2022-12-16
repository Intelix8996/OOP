package ru.nsu.nrepin;

/**
 * Program main class that contains entry point.
 */
public class Main {

    /**
     * CLI entry point. Reads a line from standard input, evaluates it and prints result.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        String string = TerminalReader.readLine();

        System.out.println(ExpressionEvaluator.evaluatePrefixExpression(string));
    }
}