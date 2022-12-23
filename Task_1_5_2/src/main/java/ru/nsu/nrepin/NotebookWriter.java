package ru.nsu.nrepin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;

/**
 * This class handles IO operation with {@code Notebook}.
 */
public class NotebookWriter {

    /**
     * Saves given notebook to JSON file.
     *
     * @param notebook notebook to save
     */
    public static void save(Notebook notebook) {

        File outputFile = new File("notebook.json");

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(notebook.toJson());
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    /**
     * Restores notebook from file.
     *
     * @return restored notebook
     */
    public static Notebook restore() {

        StringBuilder json = new StringBuilder();

        File inputFile = new File("notebook.json");

        try (FileReader reader = new FileReader(inputFile)) {

            while (true) {
                int c;

                c = reader.read();

                if (c == -1) {
                    break;
                }

                json.append((char) c);
            }
        } catch (FileNotFoundException fe) {
            return new Notebook();
        } catch (IOException e) {
            throw new IOError(e);
        }

        return Notebook.fromJson(json.toString());
    }
}
