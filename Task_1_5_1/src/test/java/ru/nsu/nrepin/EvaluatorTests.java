package ru.nsu.nrepin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EvaluatorTests {

    @Test
    public void testEval() {
        String input = "sin + - 1 2 1";

        Assertions.assertEquals(
                Double.parseDouble("0.0"),
                Double.parseDouble(ExpressionEvaluator.evaluatePrefixExpression(input))
        );
    }

    @Test
    public void testWrongGrammar() {
        String input = "sin + - 1 2 3 4";

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> ExpressionEvaluator.evaluatePrefixExpression(input)
        );
    }
}
