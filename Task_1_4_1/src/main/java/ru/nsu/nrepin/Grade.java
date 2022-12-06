package ru.nsu.nrepin;

/**
 * Define possible grades.
 */
public enum Grade {
    POOR(2),
    SATISFACTORY(3),
    GOOD(4),
    EXCELLENT(5);

    private final int value;

    Grade(int intValue) {
        value = intValue;
    }

    /**
     * Return integer equivalent of a grade.
     *
     * @return integer mark
     */
    public int intValue() {
        return value;
    }
}
