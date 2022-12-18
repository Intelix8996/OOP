package ru.nsu.nrepin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class NotebookWriter {
    public static void save(Notebook notebook) {

        File outputFile = new File(NotebookWriter.class.getResource("/notebook.json").getPath());

        PrintWriter writer;

        try {
            writer = new PrintWriter(outputFile);
        } catch (FileNotFoundException fe) {
            try {
                outputFile.createNewFile();
            } catch (IOException ioe) {
                throw new IOError(ioe);
            }

            try {
                writer = new PrintWriter(outputFile);
            } catch (FileNotFoundException fee) {
                throw new IOError(fee);
            }
        }

        writer.write(notebook.toJson());
    }

    public static Notebook restore() {
        InputStream inputStream = NotebookWriter.class.getResourceAsStream("/notebook.json");

        if (inputStream == null) {
            return new Notebook();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder json = new StringBuilder();

        while (true) {
            String buffer;

            try {
                buffer = reader.readLine();
            } catch (IOException e) {
                break;
            }

            if (buffer == null) {
                break;
            }

            json.append(buffer);
        }

        return Notebook.fromJson(json.toString());
    }
}
