package ru.nsu.nrepin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * This class represents notebook.
 */
public class Notebook {

    private final List<Record> records;

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("d.M.y H:m", Locale.ENGLISH);

    /**
     * Constructs new {@code Notebook} object.
     */
    public Notebook() {
        records = new ArrayList<>();
    }

    /**
     * Creates new record in notebook with given name and text.
     *
     * @param name new note name
     * @param note now note text
     */
    public void add(String name, String note) {
        records.add(new Record(name, note));
    }

    /**
     * Creates new record in notebook with given name, text and timestamp.
     *
     * @param creationTime new note timestamp
     * @param name new note name
     * @param note new note text
     */
    public void add(String creationTime, String name, String note) {
        records.add(new Record(LocalDateTime.parse(creationTime, formatter), name, note));
    }

    /**
     * Removes a record from notebook with given name.
     *
     * @param name name to search
     * @return {@code true} if note was found and remove, {@code false} otherwise
     */
    public boolean remove(String name) {
        for (Record record : records) {
            if (record.getName().equals(name)) {
                records.remove(record);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a printable string representation of notebook with records sorted by creation time.
     *
     * @return string representation of notebook
     */
    public String toSortedString() {
        List<Record> sortedRecords = records.stream()
                .sorted(Comparator.comparing(Record::getCreationTime))
                .collect(Collectors.toList());

        return makeString(sortedRecords);
    }

    /**
     * Returns a printable string representation of notebook with records sorted by creation time
     * and filtered by creation time and keywords.
     *
     * @return string representation of notebook
     */
    public String toSortedFilteredString(
            LocalDateTime from,
            LocalDateTime to,
            List<String> keywords
    ) {
        List<Record> sortedRecords = records.stream()
                .sorted(Comparator.comparing(Record::getCreationTime))
                .filter(record -> record.getCreationTime().compareTo(from) > 0)
                .filter(record -> record.getCreationTime().compareTo(to) < 0)
                .filter(record -> {
                    if (keywords.size() == 0) {
                        return true;
                    }

                    for (String keyword : keywords) {
                        if (record.getName().toLowerCase().contains(keyword.toLowerCase())) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());

        return makeString(sortedRecords);
    }

    private String makeString(List<Record> records) {
        StringBuilder builder = new StringBuilder();

        for (Record record : records) {
            builder.append(record.getCreationTime().format(formatter));
            builder.append(" ");
            builder.append(record.getName());
            builder.append(": ");
            builder.append(record.getText());
            builder.append("\n");
        }

        return builder.toString();
    }

    /**
     * Serializes current object to JSON string.
     *
     * @return JSON string
     */
    public String toJson() {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter());

        Gson gson = builder.setPrettyPrinting().create();

        return gson.toJson(this);
    }

    /**
     * Restores {@code Notebook} object from JSON string.
     *
     * @param json JSON string
     * @return {@code Notebook} object
     */
    public static Notebook fromJson(String json) {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter());

        Gson gson = builder.setPrettyPrinting().create();

        return gson.fromJson(json, Notebook.class);
    }

    /**
     * Returns {@code DateTimeFormatter} that this class uses.
     *
     * @return current {@code DateTimeFormatter}
     */
    public static DateTimeFormatter getDateTimeFormatter() {
        return formatter;
    }
}
