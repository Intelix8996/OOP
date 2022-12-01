package ru.nsu.nrepin;

public class Main {
    public static void main(String[] args) {
        String s = TerminalReader.readLine();

        System.out.println(ExpressionEvaluator.evaluatePrefixExpression(s));
    }
}