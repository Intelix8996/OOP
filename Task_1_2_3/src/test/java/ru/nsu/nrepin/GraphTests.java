package ru.nsu.nrepin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class for Graph.
 */
public class GraphTests {

    private List<Graph<String, Integer>> graphs;

    /**
     * Initializes given graph from {@code graph.txt}.
     *
     * @param graph graph to be initialized
     */
    public void constructGraph(Graph<String, Integer> graph) {
        List<List<String>> inputGraph;

        try {
            inputGraph = GraphReader.readGraph("/graph.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

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
    }

    /**
     * Creates new graphs.
     */
    @BeforeEach
    public void createGraphs() {
        graphs = new ArrayList<>();

        graphs.add(new AdjacencyListsGraph<>());
        graphs.add(new AdjacencyMatrixGraph<>());
        graphs.add(new IncidenceMatrixGraph<>());

        for (var graph : graphs) {
            constructGraph(graph);
        }
    }

    /**
     * Tests Dijkstra algorithm on graphs.
     */
    @Test
    public void testDijkstra() {
        for (var graph : graphs) {

            Map<Node<String>, Double> graphDistances = GraphUtilities.Dijkstra(graph, graph.find("C"));
            Map<Node<String>, Double> graphRefDistances = new HashMap<>();

            graphRefDistances.put(graph.find("C"), 0.0);
            graphRefDistances.put(graph.find("E"), 4.0);
            graphRefDistances.put(graph.find("G"), 9.0);
            graphRefDistances.put(graph.find("A"), 14.0);
            graphRefDistances.put(graph.find("D"), 2.0);
            graphRefDistances.put(graph.find("B"), 10.0);
            graphRefDistances.put(graph.find("F"), 5.0);

            Assertions.assertEquals(graphRefDistances, graphDistances);
        }
    }

    /**
     * Tests other graph methods.
     */
    @Test
    public void testGraphTypes() {
        for (var graph : graphs) {
            Assertions.assertEquals(20, graph.getEdgesCount());
            Assertions.assertEquals(7, graph.getVertexCount());

            Assertions.assertEquals(graph.getEdgesCount(), graph.getEdges().size());

            Assertions.assertNull(graph.find("AG"));

            graph.removeVertex(graph.find("D"));

            Assertions.assertNull(graph.find("D"));

            Assertions.assertEquals(
                    graph.getEdge(graph.find("A"), graph.find("G")).getWeight().doubleValue(),
                    25.0
            );

            Assertions.assertFalse(graph.removeEdge(graph.find("A"), graph.find("C")));

            graph.removeEdge(graph.find("A"), graph.find("G"));

            Assertions.assertNull(graph.getEdge(graph.find("A"), graph.find("G")));
        }
    }

    /**
     * Tests auxiliary types.
     */
    @Test
    public void testBasicTypes() {
        Node<Integer> node = new Node<>(15);

        Assertions.assertEquals(15, node.getValue());

        node.setValue(10);

        Assertions.assertEquals(10, node.getValue());

        Edge<Integer> edge = new Edge<>(100);

        Assertions.assertEquals(100, edge.getWeight());

        edge.setWeight(5);

        Assertions.assertEquals(5, edge.getWeight());

        Pair<String, Integer> pair = new Pair<>("A", 15);

        Assertions.assertEquals("A", pair.getLeft());
        Assertions.assertEquals(15, pair.getRight());

        pair.setRight(2);
        pair.setLeft("B");

        Assertions.assertEquals("B", pair.getLeft());
        Assertions.assertEquals(2, pair.getRight());
    }
}
