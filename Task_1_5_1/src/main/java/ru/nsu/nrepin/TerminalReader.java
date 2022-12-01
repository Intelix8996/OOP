package ru.nsu.nrepin;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalReader {

    public static String readLine() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new IOError(e);
        }

        return line;
    }
}
