package ru.nsu.nrepin.operations;

import ru.nsu.nrepin.Operation;

import java.util.List;

/**
 * Multiplies two numbers.
 */
public class Multiply implements Operation {
    private static final int OPERAND_COUNT = 2;

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
        double b = Double.parseDouble(operands.get(1));

        return String.valueOf(a * b);
    }
}
