package ru.nsu.nrepin;

import java.time.LocalDateTime;

public class Record {

    private final LocalDateTime creationTime;
    private final String name;
    private final String text;

    public Record(String name, String text) {
        this.name = name;
        this.text = text;

        this.creationTime = LocalDateTime.now();
    }

    public Record(LocalDateTime creationTime, String name, String text) {
        this.creationTime = creationTime;
        this.name = name;
        this.text = text;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
