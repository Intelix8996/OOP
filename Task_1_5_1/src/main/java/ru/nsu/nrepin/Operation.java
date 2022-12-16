package ru.nsu.nrepin;

import java.util.List;

/**
 * Interface for all operations.
 */
public interface Operation {

    /**
     * Returns operand count of operation.
     *
     * @return operand count
     */
    int getOperandCount();

    /**
     * Performs operation on list of operands.
     *
     * @param operands operands
     * @return result of operation as {@code String}
     */
    String compute(List<String> operands);
}
