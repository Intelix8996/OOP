package ru.nsu.nrepin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for RecordBook.
 */
public class RecordBookTests {

    private RecordBook book;

    /**
     * Create an instance of class for testing.
     */
    @BeforeEach
    public void createRecordBook() {
        book = new RecordBook();
    }

    private void fillWithRealData() {
        book.putMark(1, "Calculus", Grade.GOOD);
        book.putMark(1, "Discrete Math", Grade.SATISFACTORY);
        book.putMark(1, "History", Grade.EXCELLENT);
        book.putMark(1, "Declarative Programming", Grade.EXCELLENT);
        book.putMark(1, "Russian Language", Grade.EXCELLENT);
        book.putMark(1, "Imperative Programming", Grade.EXCELLENT);

        book.putMark(2, "Calculus", Grade.GOOD);
        book.putMark(2, "Imperative Programming", Grade.EXCELLENT);
        book.putMark(2, "Digital Platforms", Grade.EXCELLENT);
        book.putMark(2, "Graph Theory", Grade.EXCELLENT);
        book.putMark(2, "SQL", Grade.EXCELLENT);
        book.putMark(2, "English Language", Grade.SATISFACTORY);
    }

    /**
     * Test on real data.
     */
    @Test
    public void testRealData() {

        fillWithRealData();

        Assertions.assertFalse(book.canHaveHonoursDegree());
        Assertions.assertTrue(book.canHaveEnhancedStipend(1));
        Assertions.assertFalse(book.canHaveEnhancedStipend(2));
        Assertions.assertEquals(4.5, book.getAverageScore());
    }

    /**
     * Test canHaveHonoursDegree and putQualificationWorkMark methods.
     */
    @Test
    public void testQualificationWorkAndHonoursDegree() {

        fillWithRealData();

        book.putMark(2, "English Language", Grade.GOOD);
        book.putMark(1, "Discrete Math", Grade.GOOD);

        Assertions.assertFalse(book.canHaveHonoursDegree());

        book.putMark(2, "English Language", Grade.EXCELLENT);
        book.putMark(1, "Discrete Math", Grade.EXCELLENT);

        Assertions.assertTrue(book.canHaveHonoursDegree());

        book.putQualificationWorkMark(Grade.EXCELLENT);

        Assertions.assertTrue(book.canHaveHonoursDegree());

        book.putQualificationWorkMark(Grade.SATISFACTORY);

        Assertions.assertFalse(book.canHaveHonoursDegree());
    }

    /**
     * Test wrong semester number.
     */
    @Test
    public void testWrongSemester() {
        RecordBook book = new RecordBook();

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> book.putMark(16, "Calculus", Grade.GOOD)
        );

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> book.canHaveEnhancedStipend(15)
        );
    }
}
