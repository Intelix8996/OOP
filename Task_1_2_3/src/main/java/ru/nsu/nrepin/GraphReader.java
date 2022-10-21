package ru.nsu.nrepin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is used to handle IO operations with graphs.
 */
public class GraphReader {

    /**
     * Reads a graph from specified file.
     * Forms list of tokens read from file.
     * First entry is description of vertices, tokens are vertices values.
     * All subsequent entries are triples that represent edges (origin, terminus, weight).
     *
     * @param filename file to read from
     * @return data structure that represent graph
     * @throws FileNotFoundException is thrown if file was not found
     */
    public static List<List<String>> readGraph(String filename) throws FileNotFoundException {

        InputStream inputStream = GraphReader.class.getResourceAsStream(filename);

        if (inputStream == null) {
            throw new IOError(null);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

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
