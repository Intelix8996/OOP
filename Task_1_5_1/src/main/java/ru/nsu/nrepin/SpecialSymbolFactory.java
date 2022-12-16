package ru.nsu.nrepin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
     * Returns special symbol value.
     *
     * @param symbol symbol
     * @return value as {@code String}
     */
    public static String getSpecialSymbol(String symbol) {

        symbol = symbol.toLowerCase();

        if (SPECIAL_SYMBOL_SET.containsKey(symbol)) {
            return String.valueOf(SPECIAL_SYMBOL_SET.get(symbol));
        } else {
            throw new IllegalStateException(symbol + " not a valid symbol");
        }
    }

    /**
     * Returns all supported special symbols as a {@code Set}.
     *
     * @return a {@code Set} of supported special symbols
     */
    public static Set<String> getSymbolSet() {
        return SPECIAL_SYMBOL_SET.keySet();
    }
}
