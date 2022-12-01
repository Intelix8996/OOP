package ru.nsu.nrepin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class ExpressionEvaluator {

    private static List<String> tokenize(String string) {
        return new ArrayList<>(List.of(string.split(" ")));
    }

    public static String evaluatePrefixExpression(String expression) {

        List<String> tokens = tokenize(expression);

        Set<String> supportedOperations = OperationFactory.getOperationSet();

        Collections.reverse(tokens);

        Stack<String> stack = new Stack<>();

        for (String token : tokens) {

            if (supportedOperations.contains(token)) {

                Operation operation = OperationFactory.getOperation(token);

                List<String> operands = new ArrayList<>();

                for (int i = 0; i < operation.getOperandCount(); ++i) {
                    operands.add(stack.pop());
                }

                stack.push(operation.compute(operands));
            } else {
                stack.push(token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalStateException();
        } else {
            return stack.pop();
        }
    }
}
