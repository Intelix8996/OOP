package ru.nsu.nrepin.operations;

import java.util.List;
import ru.nsu.nrepin.Operation;

/**
 * Returns a square root of given value.
 */
public class SquareRoot implements Operation {
    private static final int OPERAND_COUNT = 1;

    @Override
    public int getOperandCount() {
        return OPERAND_COUNT;
    }

    @Override
    public String compute(List<String> operands) {
        if (operands.size() != OPERAND_COUNT) {
            throw new IllegalStateException();
        }

        double a = Double.parseDouble(operands.get(0));

        return String.valueOf(Math.sqrt(a));
    }
}
