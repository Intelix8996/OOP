package ru.nsu.nrepin;

import java.time.LocalDateTime;

/**
 * This class represents single record (note) in notebook.
 */
public class Record {

    private final LocalDateTime creationTime;
    private final String name;
    private final String text;

    /**
     * Creates new {@code Record} object with given name and text.
     *
     * @param name name of new record
     * @param text text of new record
     */
    public Record(String name, String text) {
        this.name = name;
        this.text = text;

        this.creationTime = LocalDateTime.now();
    }

    /**
     * Creates new {@code Record} object with given name, text and timestamp.
     *
     * @param creationTime timestamp of new record
     * @param name name of new record
     * @param text text of new record
     */
    public Record(LocalDateTime creationTime, String name, String text) {
        this.creationTime = creationTime;
        this.name = name;
        this.text = text;
    }

    /**
     * Returns timestamp of current record.
     *
     * @return timestamp of current record
     */
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    /**
     * Returns name of current record.
     *
     * @return name of current record
     */
    public String getName() {
        return name;
    }

    /**
     * Returns text of current record.
     *
     * @return text of current record
     */
    public String getText() {
        return text;
    }
}
