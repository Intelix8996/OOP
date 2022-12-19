package ru.nsu.nrepin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import ru.nsu.nrepin.operations.Add;
import ru.nsu.nrepin.operations.Cosine;
import ru.nsu.nrepin.operations.Divide;
import ru.nsu.nrepin.operations.Logarithm;
import ru.nsu.nrepin.operations.Multiply;
import ru.nsu.nrepin.operations.Power;
import ru.nsu.nrepin.operations.Sine;
import ru.nsu.nrepin.operations.SquareRoot;
import ru.nsu.nrepin.operations.Subtract;

/**
 * Factory for operations.
 */
public class OperationFactory {

    private static final Map<String, Operation> OPERATION_SET;

    static {
        OPERATION_SET = new HashMap<>();

        OPERATION_SET.put("+", new Add());
        OPERATION_SET.put("-", new Subtract());
        OPERATION_SET.put("*", new Multiply());
        OPERATION_SET.put("/", new Divide());
        OPERATION_SET.put("sin", new Sine());
        OPERATION_SET.put("cos", new Cosine());
        OPERATION_SET.put("log", new Logarithm());
        OPERATION_SET.put("pow", new Power());
        OPERATION_SET.put("sqrt", new SquareRoot());
    }

    /**
     * Returns {@code Operation} singleton wrapped in {@code Optional} by given operator token.
     *
     * @param operator operator token
     * @return {@code Optional} of {@code Operation} singleton
     */
    public static Optional<Operation> getOperation(String operator) {
        return Optional.ofNullable(OPERATION_SET.get(operator));
    }
}
