package ru.nsu.nrepin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Factory for special symbols.
 */
public class SpecialSymbolFactory {

    private static final Map<String, Double> SPECIAL_SYMBOL_SET;

    static {
        SPECIAL_SYMBOL_SET = new HashMap<>();

        SPECIAL_SYMBOL_SET.put("e", Math.E);
        SPECIAL_SYMBOL_SET.put("pi", Math.PI);
    }

    /**
     * Returns special symbol value as {@code Optional}.
     *
     * @param symbol symbol
     * @return {@code Optional} of value as {@code String}
     */
    public static Optional<String> getSpecialSymbol(String symbol) {

        symbol = symbol.toLowerCase();

        Double symbolValue = SPECIAL_SYMBOL_SET.get(symbol);

        if (symbolValue != null) {
            return Optional.of(String.valueOf(symbolValue));
        } else {
            return Optional.empty();
        }
    }
}
