package ru.nsu.nrepin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for expression evaluator.
 */
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
    public void testComplex() {
        String input = "sqrt pow + 1 / * 1 cos 0 1 10";

        Assertions.assertEquals(
                Double.parseDouble("32.0"),
                Double.parseDouble(ExpressionEvaluator.evaluatePrefixExpression(input))
        );
    }

    @Test
    public void testLog() {
        String input = "log e";

        Assertions.assertEquals(
                Double.parseDouble("1"),
                Double.parseDouble(ExpressionEvaluator.evaluatePrefixExpression(input))
        );
    }

    @Test
    public void testWrongGrammar() {
        String inputOverflow = "sin + - 1 2 3 4";
        String inputUnderflow = "sin + - 1 2";

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> ExpressionEvaluator.evaluatePrefixExpression(inputOverflow)
        );

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> ExpressionEvaluator.evaluatePrefixExpression(inputUnderflow)
        );
    }

    @Test
    public void testInvalidSymbol() {
        String input = "log eee";

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> ExpressionEvaluator.evaluatePrefixExpression(input)
        );
    }
}
