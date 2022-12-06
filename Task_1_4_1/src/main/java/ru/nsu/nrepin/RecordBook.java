package ru.nsu.nrepin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements student's record book.
 */
public class RecordBook {

    private static final int DEFAULT_SEMESTER_COUNT = 8;

    private final List<Map<String, Grade>> semesters;

    private final int semesterCount;

    private Grade qualificationWorkMark;

    private boolean qualificationWorkDone;

    /**
     * Constructs new RecordBook with given semester count.
     *
     * @param semesterCount number of semesters.
     */
    public RecordBook(int semesterCount) {
        semesters = new ArrayList<>(semesterCount);
        this.semesterCount = semesterCount;
        qualificationWorkDone = false;

        for (int i = 0; i < semesterCount; ++i) {
            semesters.add(new HashMap<>());
        }
    }

    /**
     * Constructs new RecordBook with default (8 for bachelor's degree) semester count.
     */
    public RecordBook() {
        this(DEFAULT_SEMESTER_COUNT);
    }

    /**
     * Adds new subject to given semester and puts mark there.
     * <i>Note: semester counts from 1</i>.
     *
     * @param semester subject semester
     * @param subject subject name
     * @param mark mark to put
     */
    public void putMark(int semester, String subject, Grade mark) {
        if (semester > semesterCount || semester < 1) {
            throw new IndexOutOfBoundsException();
        }

        semesters.get(semester - 1).put(subject, mark);
    }

    /**
     * Rates qualification work.
     *
     * @param mark qualification work mark
     */
    public void putQualificationWorkMark(Grade mark) {
        qualificationWorkDone = true;
        qualificationWorkMark = mark;
    }

    /**
     * Get an average score.
     *
     * @return average score.
     */
    public float getAverageScore() {
        float score = 0;
        int marksCount = 0;

        for (Map<String, Grade> semester : semesters) {
            for (Grade grade : semester.values()) {
                marksCount++;
                score += grade.intValue();
            }
        }

        return score / marksCount;
    }

    /**
     * Check if student can have enhanced stipend in given semester.
     *
     * @param semester semester to check
     * @return {@code true} if student can have an enhanced stipend, {@code false} otherwise
     */
    public boolean canHaveEnhancedStipend(int semester) {
        if (semester > semesterCount || semester < 1) {
            throw new IndexOutOfBoundsException();
        }

        if (semester == 1) {
            return true;
        }

        boolean answer = true;

        for (Grade grade : semesters.get(semester - 2).values()) {
            if (grade.intValue() <= 3) {
                answer = false;
                break;
            }
        }

        return answer;
    }

    /**
     * Check if student can get a degree with honour.
     *
     * @return {@code true} if student can get a degree with honour, {@code false} otherwise
     */
    public boolean canHaveHonoursDegree() {
        for (Map<String, Grade> semester : semesters) {
            for (Grade grade : semester.values()) {
                if (grade.intValue() <= 3) {
                    return false;
                }
            }
        }

        if (getAverageScore() < 4.75) {
            return false;
        }

        if (qualificationWorkDone && qualificationWorkMark.intValue() < 5) {
            return false;
        }

        return true;
    }
}
