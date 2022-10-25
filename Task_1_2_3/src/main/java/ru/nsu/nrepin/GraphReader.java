package ru.nsu.nrepin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * This class is used to handle IO operations with graphs.
 */
public class GraphReader {

    /**
     * File formats supported by this class.
     */
    public enum FileFormat {
        /**
         * Graph represented by adjacency lists.
         * In first line of the file there are <b>n</b> vertices.
         * Then there are <b>n</b> lines.
         * Each line represents adjacent nodes of <b>n</b>th vertex.
         * Each entry in this line has format V|W, where V is terminus node and W is edge weight.
         */
        ADJACENCY_LISTS,
        /**
         * Graph represented by adjacency matrix.
         * In first line of the file there are <b>n</b> vertices.
         * Then there are <b>n</b> lines with <b>n</b> entries each that represent adjacency matrix.
         * If an edge between <i>u</i> and <i>v</i> is present
         * then its weight is on <i>u</i>th and <i>v</i>th places in the matrix.
         * Absence of edge is represented with '-' symbol
         */
        ADJACENCY_MATRIX,
        /**
         * Graph represented by edges list.
         * First line is description of vertices, tokens are vertices values.
         * All subsequent entries are triples that represent edges (origin, terminus, weight).
         */
        EDGES_LIST
    }

    /**
     * Reads tokens from specified file line by line.
     *
     * @param filename file to read from
     * @return lists of tokens
     * @throws FileNotFoundException is thrown if file was not found
     */
    public static List<List<String>> readTokens(String filename) throws FileNotFoundException {

        InputStream inputStream = GraphReader.class.getResourceAsStream(filename);

        if (inputStream == null) {
            throw new FileNotFoundException("Can't open graph file");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        List<List<String>> lines = new ArrayList<>();

        while (true) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                break;
            }

            if (line != null) {
                List<String> currentLine = new ArrayList<>();

                lines.add(currentLine);

                Collections.addAll(currentLine, line.split(" "));
            } else {
                break;
            }

        }

        return lines;
    }

    /**
     * Fills given graph with data from given file in <i>resources</i> folder.
     *
     * @param graph graph object to fill
     * @param filename name of file to read from
     * @param type type of graph representation in file
     */
    public static void fillGraphFromFile(Graph<String, Integer> graph,
                                         String filename,
                                         FileFormat type) {
        List<List<String>> inputGraph;

        try {
            inputGraph = readTokens(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<String> nodes = new ArrayList<>();

        for (String node : inputGraph.get(0)) {
            graph.addVertex(node);
            nodes.add(node);
        }

        inputGraph.remove(0);

        switch (type) {
            case ADJACENCY_LISTS:
                for (int i = 0; i < nodes.size(); ++i) {
                    for (int j = 0; j < inputGraph.get(i).size(); ++j) {
                        String v = inputGraph.get(i).get(j).split("\\|")[0];
                        String w = inputGraph.get(i).get(j).split("\\|")[1];

                        graph.addEdge(graph.find(nodes.get(i)), graph.find(v), Integer.valueOf(w));
                    }
                }
                break;
            case ADJACENCY_MATRIX:
                for (int i = 0; i < nodes.size(); ++i) {
                    for (int j = 0; j < nodes.size(); ++j) {
                        String w = inputGraph.get(i).get(j);

                        if (!Objects.equals(w, "-")) {
                            int parsedWeight;

                            try {
                                parsedWeight = Integer.parseInt(w);
                            } catch (NumberFormatException e) {
                                throw new NumberFormatException(
                                        "Illegal symbol in input file: '" + w + "'"
                                );
                            }

                            graph.addEdge(
                                    graph.find(nodes.get(i)),
                                    graph.find(nodes.get(j)),
                                    parsedWeight
                            );
                        }
                    }
                }
                break;
            case EDGES_LIST:
                for (List<String> edge : inputGraph) {
                    String u = edge.get(0);
                    String v = edge.get(1);
                    Integer w = Integer.valueOf(edge.get(2));

                    graph.addEdge(graph.find(u), graph.find(v), w);
                }
                break;
            default:
                throw new IllegalStateException();
        }
    }
}
