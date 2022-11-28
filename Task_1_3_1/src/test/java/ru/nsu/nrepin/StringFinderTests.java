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
            answer = StringUtils.findSubstringsInFile("/test1.txt", "cake");
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
            answer = StringUtils.findSubstringsInFile("/test2.txt", "cake");
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
            answer = StringUtils.findSubstringsInFile("/test3.txt", "cake");
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
            answer = StringUtils.findSubstringsInFile("/test4.txt", "cake");
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
        List<Integer> answer;

        try {
            answer = StringUtils.findSubstringsInFile("/test5.txt", "cake");
        } catch (Exception ignore) {
            throw new IOError(new FileNotFoundException());
        }

        List<Integer> referenceList = List.of();

        Assertions.assertEquals(referenceList, answer);
    }

    /**
     * Test a case when pattern repeats in the file.
     */
    @Test
    public void testRepeat() {
        List<Integer> answer;

        try {
            answer = StringUtils.findSubstringsInFile("/test6.txt", "cake");
        } catch (Exception ignore) {
            throw new IOError(new FileNotFoundException());
        }

        List<Integer> referenceList = List.of(9, 13, 17);

        Assertions.assertEquals(referenceList, answer);
    }

    /**
     * Test short file.
     */
    @Test
    public void testShort() {
        List<Integer> answer;

        try {
            answer = StringUtils.findSubstringsInFile("/test7.txt", "cake");
        } catch (Exception ignore) {
            throw new IOError(new FileNotFoundException());
        }

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
                () -> StringUtils.findSubstringsInFile("file.txt", "cake"));
    }

    /**
     * Test {@code computeZedFunction} itself.
     */
    @Test
    public void testComputeZedFunction() {
        int[] refArray = {7, 0, 1, 0, 3, 0, 1};
        String string = "abacaba";

        int[] zf = StringUtils.computeZedFunction(string);

        Assertions.assertArrayEquals(refArray, zf);
    }
}
