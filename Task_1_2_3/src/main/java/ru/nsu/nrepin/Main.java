package ru.nsu.nrepin;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<List<String>> inputGraph;

        try {
            inputGraph = GraphReader.readGraph("C:\\Users\\comp_i5\\Desktop\\OOP\\Task_1_2_3\\src\\main\\resources\\graph.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Graph<String, Integer> graph = new AdjacencyMatrixGraph<>();

        for (var node : inputGraph.get(0)) {
            graph.addVertex(node);
        }

        inputGraph.remove(0);

        for (var edge : inputGraph) {
            String u = edge.get(0);
            String v = edge.get(1);
            Integer w = Integer.valueOf(edge.get(2));

            graph.addEdge(graph.find(u), graph.find(v), w);
        }

        var dij = GraphUtilities.Dijkstra(graph, graph.find("C"));

        for (var key : dij.keySet()) {
            System.out.println(key.getValue() + " " + dij.get(key));
        }
    }
}