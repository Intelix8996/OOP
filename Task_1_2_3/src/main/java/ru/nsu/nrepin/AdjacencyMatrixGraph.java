package ru.nsu.nrepin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements graph with adjacency matrix representation.
 *
 * @param <V> type of value stored in graph node
 * @param <W> type of edge weight
 */
public class AdjacencyMatrixGraph<V, W extends Number> implements Graph<V, W> {

    private final Map<Node<V>, Map<Node<V>, Edge<W>>> adjacencyMatrix;

    /**
     * Constructs new empty graph.
     */
    public AdjacencyMatrixGraph() {
        adjacencyMatrix = new HashMap<>();
    }

    @Override
    public Edge<W> addEdge(Node<V> u, Node<V> v, W weight) {

        GraphChecker.checkNull("Nodes must not be null", u, v);
        GraphChecker.checkPresence("Nodes must be present in the graph", this, u, v);

        Edge<W> newEdge = new Edge<>(weight);

        adjacencyMatrix.get(u).put(v, newEdge);

        return newEdge;
    }

    @Override
    public Edge<W> getEdge(Node<V> u, Node<V> v) {
        GraphChecker.checkNull("Nodes must not be null", u, v);
        GraphChecker.checkPresence("Nodes must be present in the graph", this, u, v);

        return adjacencyMatrix.get(u).get(v);
    }

    @Override
    public List<Edge<W>> getNodeEdges(Node<V> u) {

        GraphChecker.checkNull("Node must not be null", u);
        GraphChecker.checkPresence("Node must be present in the graph", this, u);

        List<Edge<W>> edges = new ArrayList<>();
        Map<Node<V>, Edge<W>> node = adjacencyMatrix.get(u);

        for (Node<V> edge : node.keySet()) {
            if (node.get(edge) != null) {
                edges.add(node.get(edge));
            }
        }

        return edges;
    }

    @Override
    public Node<V> getEdgeTerminus(Node<V> u, Edge<W> e) {

        GraphChecker.checkNull("Node must not be null", u);
        GraphChecker.checkNull("Edge must not be null", e);

        GraphChecker.checkPresence("Node must be present in the graph", this, u);

        Map<Node<V>, Edge<W>> node = adjacencyMatrix.get(u);

        for (Node<V> i : node.keySet()) {
            if (node.get(i) != null && node.get(i).equals(e)) {
                return i;
            }
        }

        return null;
    }

    @Override
    public List<Edge<W>> getEdges() {
        List<Edge<W>> edges = new ArrayList<>();

        for (Node<V> i : adjacencyMatrix.keySet()) {
            for (Node<V> j : adjacencyMatrix.get(i).keySet()) {
                if (adjacencyMatrix.get(i).get(j) != null) {
                    edges.add(adjacencyMatrix.get(i).get(j));
                }
            }
        }

        return edges;
    }

    @Override
    public boolean removeEdge(Node<V> u, Node<V> v) {

        GraphChecker.checkNull("Nodes must not be null", u, v);
        GraphChecker.checkPresence("Nodes must be present in the graph", this, u, v);

        if (adjacencyMatrix.get(u).get(v) == null) {
            return false;
        }

        adjacencyMatrix.get(u).replace(v, null);

        return true;
    }

    @Override
    public Node<V> addVertex(V value) {

        Node<V> newVertex = new Node<>(value);

        for (Node<V> vertex : adjacencyMatrix.keySet()) {
            adjacencyMatrix.get(vertex).put(newVertex, null);
        }

        adjacencyMatrix.put(newVertex, new HashMap<>());

        for (Node<V> vertex : adjacencyMatrix.keySet()) {
            adjacencyMatrix.get(newVertex).put(vertex, null);
        }

        return newVertex;
    }

    @Override
    public List<Node<V>> getVertices() {
        return new ArrayList<>(adjacencyMatrix.keySet());
    }

    @Override
    public boolean removeVertex(Node<V> node) {

        GraphChecker.checkNull("Node must not be null", node);

        if (adjacencyMatrix.get(node) == null) {
            return false;
        }

        for (Node<V> vertex : adjacencyMatrix.keySet()) {
            adjacencyMatrix.get(vertex).remove(node);
        }

        adjacencyMatrix.remove(node);

        return true;
    }

    @Override
    public Node<V> find(V value) {
        for (Node<V> vertex : adjacencyMatrix.keySet()) {
            if (vertex.getValue().equals(value)) {
                return vertex;
            }
        }

        return null;
    }

    @Override
    public int getVertexCount() {
        return adjacencyMatrix.size();
    }

    @Override
    public int getEdgesCount() {
        return getEdges().size();
    }
}
