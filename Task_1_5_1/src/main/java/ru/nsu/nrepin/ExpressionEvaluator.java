package ru.nsu.nrepin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * This class handles evaluation of expression in prefix notation.
 */
public class ExpressionEvaluator {

    /**
     * Tokenizes given string.
     *
     * @param string string to split
     * @return list of tokens
     */
    private static List<String> tokenize(String string) {
        return new ArrayList<>(List.of(string.split(" ")));
    }

    /**
     * Calculate value of given expression in prefix notation.
     *
     * @param expression expression to evaluate
     * @return result as {@code String}
     */
    public static String evaluatePrefixExpression(String expression) {

        List<String> tokens = tokenize(expression);

        Set<String> supportedOperations = OperationFactory.getOperationSet();
        Set<String> specialSymbols = SpecialSymbolFactory.getSymbolSet();

        Collections.reverse(tokens);

        Stack<String> stack = new Stack<>();

        for (String token : tokens) {

            if (supportedOperations.contains(token)) {

                Operation operation = OperationFactory.getOperation(token);

                List<String> operands = new ArrayList<>();

                for (int i = 0; i < operation.getOperandCount(); ++i) {

                    String operand;

                    try {
                        operand = stack.pop();
                    } catch (EmptyStackException e) {
                        throw new IllegalStateException("Expression is not valid");
                    }

                    operands.add(operand);
                }

                stack.push(operation.compute(operands));
            } else {

                boolean isNumber = true;

                try {
                    double num = Double.parseDouble(token);
                } catch (NumberFormatException e) {
                    isNumber = false;
                }

                if (isNumber) {
                    stack.push(token);
                } else {
                    if (specialSymbols.contains(token)) {
                        stack.push(SpecialSymbolFactory.getSpecialSymbol(token));
                    } else {
                        throw new IllegalStateException("Invalid symbol: " + token);
                    }
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalStateException("Expression is not valid");
        } else {
            return stack.pop();
        }
    }
}
