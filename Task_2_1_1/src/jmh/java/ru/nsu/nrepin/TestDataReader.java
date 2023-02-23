package ru.nsu.nrepin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for reading test data.
 */
public class TestDataReader {

    private static final String FILENAME = "/data.txt";

    /**
     * Reads test data from file.
     *
     * @return list of integers
     * @throws FileNotFoundException if file is not found
     */
    public static List<Integer> loadData() throws FileNotFoundException {
        InputStream inputStream = TestDataReader.class.getResourceAsStream(FILENAME);

        if (inputStream == null) {
            throw new FileNotFoundException("Can't open graph file");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        List<Integer> lines = new ArrayList<>();

        while (true) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                break;
            }

            if (line != null) {
                lines.add(Integer.valueOf(line));
            } else {
                break;
            }

        }

        return lines;
    }
}
