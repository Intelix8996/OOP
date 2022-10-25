package ru.nsu.nrepin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Graph.
 */
public class GraphTests {

    private static final int GRAPH_COUNT = 3;

    private List<Graph<String, Integer>> graphs;

    private void testGraphEquality(Graph<String, Integer> graphA,
                                   Graph<String, Integer> graphB) {

        Assertions.assertEquals(graphA.getEdgesCount(), graphB.getEdgesCount());
        Assertions.assertEquals(graphA.getVertexCount(), graphB.getVertexCount());

        List<Node<String>> verticesA = graphA.getVertices();

        for (Node<String> node : verticesA) {
            Node<String> nodeB = graphB.find(node.getValue());

            Assertions.assertEquals(node.getValue(), nodeB.getValue());
        }

        for (Node<String> nodeA : graphA.getVertices()) {
            for (Node<String> nodeB : graphB.getVertices()) {

                Node<String> nodeBinA = graphA.find(nodeB.getValue());
                Node<String> nodeAinB = graphB.find(nodeA.getValue());

                Edge<Integer> edgeA = graphA.getEdge(nodeA, nodeBinA);
                Edge<Integer> edgeB = graphB.getEdge(nodeB, nodeAinB);

                if (edgeA == null || edgeB == null) {
                    Assertions.assertNull(edgeA);
                    Assertions.assertNull(edgeB);
                } else {
                    Assertions.assertEquals(edgeA.getWeight(), edgeB.getWeight());
                }
            }
        }
    }

    /**
     * Creates new graphs.
     */
    @BeforeEach
    public void createGraphs() {
        graphs = new ArrayList<>();

        graphs.add(new AdjacencyMatrixGraph<>());
        graphs.add(new AdjacencyListsGraph<>());
        graphs.add(new IncidenceMatrixGraph<>());

        GraphReader.fillGraphFromFile(
                graphs.get(0),
                "/EdgeListGraph.txt",
                GraphReader.FileFormat.EDGES_LIST);

        GraphReader.fillGraphFromFile(
                graphs.get(1),
                "/AdjacencyMatrixGraph.txt",
                GraphReader.FileFormat.ADJACENCY_MATRIX);

        GraphReader.fillGraphFromFile(
                graphs.get(2),
                "/AdjacencyListsGraph.txt",
                GraphReader.FileFormat.ADJACENCY_LISTS);
    }

    /**
     * Test equality of different graph formats.
     */
    @Test
    public void testVariousFormats() {
        for (int i = 0; i < GRAPH_COUNT; ++i) {
            for (int j = i; j < GRAPH_COUNT; ++j) {
                testGraphEquality(graphs.get(i), graphs.get(j));
            }
        }
    }

    /**
     * Tests Dijkstra algorithm on graphs.
     */
    @Test
    public void testDijkstra() {
        for (var graph : graphs) {
            Map<Node<String>, Double> graphRefDistances = new HashMap<>();

            graphRefDistances.put(graph.find("C"), 0.0);
            graphRefDistances.put(graph.find("E"), 4.0);
            graphRefDistances.put(graph.find("G"), 9.0);
            graphRefDistances.put(graph.find("A"), 14.0);
            graphRefDistances.put(graph.find("D"), 2.0);
            graphRefDistances.put(graph.find("B"), 10.0);
            graphRefDistances.put(graph.find("F"), 5.0);

            Map<Node<String>, Double> graphDistances = GraphUtilities.dijkstra(
                    graph,
                    graph.find("C")
            );

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
