package ru.nsu.nrepin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphReader {
    public static List<List<String>> readGraph(String filename) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        List<List<String>> lines = new ArrayList<>();

        while (true) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                break;
            }

            if (line != null) {
                List<String> currentLine = new ArrayList<>();

                lines.add(currentLine);

                Collections.addAll(currentLine, line.split(" "));
            }
            else {
                break;
            }

        }

        return lines;
    }
}
