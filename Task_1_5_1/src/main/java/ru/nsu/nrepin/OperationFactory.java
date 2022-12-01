package ru.nsu.nrepin;

import ru.nsu.nrepin.operations.Add;
import ru.nsu.nrepin.operations.Sine;
import ru.nsu.nrepin.operations.Subtract;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OperationFactory {

    private static final Map<String, Operation> OPERATION_SET;

    static {
        OPERATION_SET = new HashMap<>();

        OPERATION_SET.put("+", new Add());
        OPERATION_SET.put("-", new Subtract());
        OPERATION_SET.put("sin", new Sine());
    }

    public static Operation getOperation(String operator) {
        if (OPERATION_SET.containsKey(operator)) {
            return OPERATION_SET.get(operator);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public static Set<String> getOperationSet() {
        return OPERATION_SET.keySet();
    }
}
