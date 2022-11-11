package ru.nsu.nrepin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to find substrings in big files.
 */
public class SubstringFinder {

    /**
     * Searches all occurrences of pattern in files.
     * Returns their indices as {@code List<Integer>}.
     *
     * @param filename file
     * @param pattern substring to search
     * @return list of indices of substring occurrences
     * @throws FileNotFoundException if file was not found
     */
    public static List<Integer> findSubstringsInFile(
            String filename,
            String pattern)
            throws FileNotFoundException {

        // Create BufferedReader
        InputStream inputStream = SubstringFinder.class.getResourceAsStream(filename);

        if (inputStream == null) {
            throw new FileNotFoundException("Can't open file");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // Length of actual pattern and delimiter ('\0')
        int patternLength = pattern.length() + 1;

        // Allocate buffer
        PrefixRingBuffer buffer = new PrefixRingBuffer(patternLength, patternLength);

        // Write pattern as prefix to array region
        for (int i = 0; i < patternLength - 1; ++i) {
            buffer.set(i, pattern.charAt(i));
        }

        // Write prefix delimiter ('\0')
        buffer.set(patternLength - 1, '\0');

        // Fill ring buffer region with beginning of file
        for (int i = 0; i < patternLength; ++i) {
            int c;

            try {
                c = reader.read();
            } catch (IOException e) {
                break;
            }
            buffer.set(patternLength + i, c);
        }

        // Calculate starting position
        int position = 2 * patternLength;

        List<Integer> result = new ArrayList<>();

        boolean endOfStream = false;

        while (true) {
            int matchLength = 0;

            // Check length of matching substring
            while (buffer.get(matchLength) == buffer.get(position + matchLength)) {
                matchLength++;
            }

            // If it is equal to pattern length without a delimiter then
            // add to resulting list
            if (matchLength == patternLength - 1) {
                result.add(position - (2 * patternLength));
            }

            // If we reached end of file there is no need to continue
            if (endOfStream) {
                break;
            }

            // Otherwise, read next character and put it on current position
            int c;

            try {
                c = reader.read();
            } catch (IOException e) {
                break;
            }

            if (c == -1) {
                endOfStream = true;
            }

            buffer.set(position++, c);
        }

        return result;
    }
}
