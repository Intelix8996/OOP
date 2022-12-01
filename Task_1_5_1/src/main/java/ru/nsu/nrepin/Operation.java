package ru.nsu.nrepin;

import java.util.List;

public interface Operation {

    int getOperandCount();

    String compute(List<String> operands);
}
