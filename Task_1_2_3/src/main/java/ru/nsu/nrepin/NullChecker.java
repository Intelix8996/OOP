package ru.nsu.nrepin;

/**
 * This class is used to perform {@code null} checks.
 */
public class NullChecker {
    /**
     * Checks if any of given values are {@code null}.
     * If it is true throws {@code IllegalStateException} with given message.
     *
     * @param msg message that will be passed to exception
     * @param values values to check
     * @param <T> type of values
     */
    @SafeVarargs
    public static <T> void checkNull(String msg, T... values) {
        for (T value : values) {
            if (value == null) {
                throw new IllegalStateException(msg);
            }
        }
    }
}
