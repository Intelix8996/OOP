package ru.nsu.nrepin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to find substrings in big files.
 */
public class StringUtils {

    /**
     * Wrapper for {@code computeZedFunction} with only one argument.
     * This function is equivalent to computeZedFunction(string, 0).
     *
     * @param string string to compute Z-Function
     * @return Z-Function as array
     */
    public static int[] computeZedFunction(String string) {
        return computeZedFunction(string, 0);
    }

    /**
     * Computes Z-Function for given string from {@code startIndex}.
     * Returns an array representing resulting function.
     *
     * @param string string to compute Z-Function
     * @param startIndex where to start computing
     * @return Z-Function as array
     */
    public static int[] computeZedFunction(String string, int startIndex) {

        int stringLength = string.length();

        int[] zedFunction = new int[string.length()];

        int left = startIndex;
        int right = startIndex;

        for (int i = startIndex; i < stringLength; ++i) {
            zedFunction[i] = Math.max(0, Math.min(right - i, zedFunction[i - left]));

            while ((i + zedFunction[i] < stringLength)
                    && (string.charAt(zedFunction[i]) == string.charAt(i + zedFunction[i]))) {
                zedFunction[i]++;
            }

            if (i + zedFunction[i] > right) {
                left = i;
                right = i + zedFunction[i];
            }
        }

        return zedFunction;
    }

    /**
     * Searches all occurrences of pattern in stream from {@code Reader}.
     * Returns their indices as {@code List<Integer>}.
     *
     * @param reader source reader
     * @param pattern substring to search
     * @return list of indices of substring occurrences
     */
    public static List<Integer> findSubstrings(
            Reader reader,
            String pattern) {

        // Length of the pattern plus delimiter
        int patternLength = pattern.length() + 1;

        // Allocate buffer
        char[] buffer = new char[3 * patternLength];

        // Fill first patternLength chars with pattern
        for (int i = 0; i < patternLength - 1; ++i) {
            buffer[i] =  pattern.charAt(i);
        }

        // Put delimiter
        buffer[patternLength - 1] = 0xFFFF;

        // Allocate buffer for Z-Function
        int[] zedFunction = new int[buffer.length];

        // Iteration counter; start from -1 as it takes one additional iteration to fill the buffer
        int iterCount = -1;

        // List for result
        List<Integer> result = new ArrayList<>();

        // How many chars were read from reader
        int charsRead;

        while (true) {

            // Shift buffers by patternLength to left
            System.arraycopy(
                    buffer,
                    2 * patternLength,
                    buffer,
                    patternLength,
                    patternLength
            );

            System.arraycopy(
                    zedFunction,
                    2 * patternLength,
                    zedFunction,
                    patternLength,
                    patternLength
            );

            // Fill end of buffer with zeros as if we read less that patternLength chars from reader
            Arrays.fill(buffer, 2 * patternLength, 3 * patternLength, '\0');

            // Read patternLength chars
            try {
                charsRead = reader.read(buffer, 2 * patternLength, patternLength);
            } catch (IOException e) {
                throw new IOError(e);
            }

            // Recompute Z-Function
            zedFunction = computeZedFunction(String.valueOf(buffer), patternLength);

            // Check for matches
            for (int i = patternLength; i < 2 * patternLength; ++i) {
                if (zedFunction[i] == patternLength - 1) {
                    result.add(i - patternLength + (patternLength * iterCount));
                }
            }

            // Check for the end of the stream
            if (charsRead < patternLength - 1) {
                break;
            }

            iterCount++;
        }

        return result;
    }

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

        InputStream inputStream = StringUtils.class.getResourceAsStream(filename);

        if (inputStream == null) {
            throw new FileNotFoundException("Can't open file");
        }

        Reader reader = new BufferedReader(new InputStreamReader(inputStream));

        return findSubstrings(reader, pattern);
    }
}
