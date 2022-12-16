package ru.nsu.nrepin.operations;

import ru.nsu.nrepin.Operation;

import java.util.List;

/**
 * Raises first number to the second number's power.
 */
public class Power implements Operation {
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

        double base = Double.parseDouble(operands.get(0));
        double power = Double.parseDouble(operands.get(1));

        return String.valueOf(Math.pow(base, power));
    }
}
