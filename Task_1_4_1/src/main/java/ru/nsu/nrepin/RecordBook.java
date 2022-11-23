package ru.nsu.nrepin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordBook {

    public enum Grade {
        POOR(2),
        SATISFACTORY(3),
        GOOD(4),
        EXCELLENT(5);

        private final int value;

        Grade(int intValue) {
            value = intValue;
        }

        public int intValue() {
            return value;
        }
    }

    private static final int DEFAULT_SEMESTER_COUNT = 8;

    private final List<Map<String, Grade>> semesters;

    private final int semesterCount;

    public RecordBook(int semesterCount) {
        semesters = new ArrayList<>(semesterCount);
        this.semesterCount = semesterCount;

        for (int i = 0; i < semesterCount; ++i) {
            semesters.add(new HashMap<>());
        }
    }

    public RecordBook() {
        this(DEFAULT_SEMESTER_COUNT);
    }

    public void addMark(int semester, String subject, Grade mark) {
        if (semester > semesterCount) {
            throw new IndexOutOfBoundsException();
        }

        semesters.get(semester - 1).put(subject, mark);
    }

    public float averageScore() {
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

    public boolean canHaveEnhancedStipend(int semester) {
        if (semester > semesterCount) {
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
}
