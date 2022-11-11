package ru.nsu.nrepin;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tester class for StringFinder.
 */
public class StringFinderTests {

    /**
     * Test a case when multiple occurrences of pattern.
     */
    @Test
    public void testMultipleOccurrences() {
        List<Integer> answer;

        try {
            answer = SubstringFinder.findSubstringsInFile("/test1.txt", "cake");
        } catch (Exception ignore) {
            throw new IOError(new FileNotFoundException());
        }

        List<Integer> referenceList = List.of(9, 15, 21);

        Assertions.assertEquals(referenceList, answer);
    }

    /**
     * Test a case when occurrence of pattern.
     */
    @Test
    public void testSingleOccurrence() {
        List<Integer> answer;

        try {
            answer = SubstringFinder.findSubstringsInFile("/test2.txt", "cake");
        } catch (Exception ignore) {
            throw new IOError(new FileNotFoundException());
        }

        List<Integer> referenceList = List.of(0);

        Assertions.assertEquals(referenceList, answer);
    }

    /**
     * Test a case when pattern is at the beginning of the file.
     */
    @Test
    public void testPrefix() {
        List<Integer> answer;

        try {
            answer = SubstringFinder.findSubstringsInFile("/test3.txt", "cake");
        } catch (Exception ignore) {
            throw new IOError(new FileNotFoundException());
        }

        List<Integer> referenceList = List.of(24);

        Assertions.assertEquals(referenceList, answer);
    }

    /**
     * Test a case when pattern is at the end of the file.
     */
    @Test
    public void testPostfix() {
        List<Integer> answer;

        try {
            answer = SubstringFinder.findSubstringsInFile("/test4.txt", "cake");
        } catch (Exception ignore) {
            throw new IOError(new FileNotFoundException());
        }

        List<Integer> referenceList = List.of(0);

        Assertions.assertEquals(referenceList, answer);
    }

    /**
     * Test a case when pattern absent in the file.
     */
    @Test
    public void testNoOccurrence() {
        List<Integer> answer = null;

        try {
            answer = SubstringFinder.findSubstringsInFile("/test5.txt", "cake");
        } catch (Exception ignore) { }

        List<Integer> referenceList = List.of();

        Assertions.assertEquals(referenceList, answer);
    }

    /**
     * Test a case when filename is wrong.
     */
    @Test
    public void testWrongFilename() {
        Assertions.assertThrows(
                FileNotFoundException.class,
                () -> SubstringFinder.findSubstringsInFile("file.txt", "cake"));
    }
}
