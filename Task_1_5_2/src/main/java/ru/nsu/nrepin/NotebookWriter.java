package ru.nsu.nrepin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class NotebookWriter {
    public static void save(Notebook notebook) {

        File outputFile = new File("notebook.json");

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(notebook.toJson());
        } catch (FileNotFoundException fe) {
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                throw new IOError(e);
            }
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(notebook.toJson());
            } catch (IOException e) {
                throw new IOError(e);
            }
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    public static Notebook restore() {

        StringBuilder json = new StringBuilder();

        File inputFile = new File("notebook.json");

        try (FileReader reader = new FileReader(inputFile)){

            while (true) {
                int c;

                c = reader.read();

                if (c == -1) {
                    break;
                }

                json.append((char)c);
            }
        } catch (FileNotFoundException fe) {
            return new Notebook();
        } catch (IOException e) {
            throw new IOError(e);
        }

        return Notebook.fromJson(json.toString());
    }
}
