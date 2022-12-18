package ru.nsu.nrepin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Notebook {

    private final List<Record> records;

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("d.M.y H:m", Locale.ENGLISH);

    public Notebook() {
        records = new ArrayList<>();
    }

    public void add(String name, String note) {
        records.add(new Record(name, note));
    }

    public void add(String creationTime, String name, String note) {
        records.add(new Record(LocalDateTime.parse(creationTime, formatter), name, note));
    }

    public boolean remove(String name) {
        for (Record record : records) {
            if (record.getName().equals(name)) {
                records.remove(record);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
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

    public String toJson() {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter());

        Gson gson = builder.setPrettyPrinting().create();

        return gson.toJson(this);
    }

    public static Notebook fromJson(String json) {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter());

        Gson gson = builder.setPrettyPrinting().create();

        return gson.fromJson(json, Notebook.class);
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return formatter;
    }
}
