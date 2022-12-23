package ru.nsu.nrepin;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

/**
 * This class test {@code Notebook}.
 */
public class NotebookTests {

    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    public static void createEmptyNotebook() {
        Notebook notebook = new Notebook();

        NotebookWriter.save(notebook);
    }

    @BeforeAll
    public static void redirectOutputStream() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterAll
    public static void restoreOutputStream() {
        System.setOut(new PrintStream(System.out));
    }

    @Test
    public void testNotebook() {
        Main.main(new String[]{"-add", "First note", "This is my first note"});
        Main.main(new String[]{"-add", "Second note", "This is my second note"});

        Main.main(new String[]{"-show"});

        String currentTime = LocalDateTime.now().format(Notebook.getDateTimeFormatter());

        Assertions.assertEquals(
                currentTime + " First note: This is my first note\n" +
                        currentTime + " Second note: This is my second note",
                outputStream.toString().strip()
        );

        outputStream.reset();

        Main.main(new String[]{"-rm", "First note"});

        Main.main(new String[]{"-show"});

        Assertions.assertEquals(
                currentTime + " Second note: This is my second note",
                outputStream.toString().strip()
        );

        Notebook notebook = new Notebook();

        notebook.add("23.12.2001 19:19", "Old note", "Very old note");
        notebook.add("23.12.2022 23:15", "Modern note", "Modern note");
        notebook.add("23.12.2022 23:16", "Another note", "Also modern note");
        notebook.add("23.12.2024 23:16", "Modern note", "Modern note, but from future");

        NotebookWriter.save(notebook);

        outputStream.reset();

        Main.main(new String[]{"-show", "23.12.2022 23:10", "23.12.2022 23:20", "MoDerN"});

        Assertions.assertEquals(
                "23.12.2022 23:15 Modern note: Modern note",
                outputStream.toString().strip()
        );
    }
}
