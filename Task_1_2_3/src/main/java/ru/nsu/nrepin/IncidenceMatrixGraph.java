package ru.nsu.nrepin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class implements graph with incidence matrix representation.
 *
 * @param <V> type of value stored in graph node
 * @param <W> type of edge weight
 */
public class IncidenceMatrixGraph<V, W extends Number> implements Graph<V, W> {

    private final Map<Node<V>, Map<Edge<W>, EdgeDirection>> incidenceMatrix;

    private enum EdgeDirection {
        INGOING,
        OUTGOING,
        NO_EDGE
    }

    /**
     * Constructs new empty graph.
     */
    public IncidenceMatrixGraph() {
        incidenceMatrix = new HashMap<>();
    }

    @Override
    public Edge<W> addEdge(Node<V> u, Node<V> v, W weight) {
        GraphChecker.checkNull("Nodes must not be null", u, v);
        GraphChecker.checkPresence("Nodes must be present in the graph", this, u, v);

        Edge<W> newEdge = new Edge<>(weight);

        for (Node<V> node : incidenceMatrix.keySet()) {
            incidenceMatrix.get(node).put(newEdge, EdgeDirection.NO_EDGE);
        }

        incidenceMatrix.get(u).put(newEdge, EdgeDirection.OUTGOING);
        incidenceMatrix.get(v).put(newEdge, EdgeDirection.INGOING);

        return newEdge;
    }

    @Override
    public Edge<W> getEdge(Node<V> u, Node<V> v) {
        GraphChecker.checkNull("Nodes must not be null", u, v);
        GraphChecker.checkPresence("Nodes must be present in the graph", this, u, v);

        for (Edge<W> edge : incidenceMatrix.get(u).keySet()) {
            if (incidenceMatrix.get(u).get(edge) == EdgeDirection.OUTGOING
                    && incidenceMatrix.get(v).get(edge) == EdgeDirection.INGOING) {
                return edge;
            }
        }

        return null;
    }

    @Override
    public List<Edge<W>> getNodeEdges(Node<V> u) {
        GraphChecker.checkNull("Node must not be null", u);
        GraphChecker.checkPresence("Node must be present in the graph", this, u);

        List<Edge<W>> edges = new ArrayList<>();

        for (Edge<W> edge : incidenceMatrix.get(u).keySet()) {
            if (incidenceMatrix.get(u).get(edge) == EdgeDirection.OUTGOING) {
                edges.add(edge);
            }
        }

        return edges;
    }

    @Override
    public Node<V> getEdgeTerminus(Node<V> u, Edge<W> e) {

        GraphChecker.checkNull("Node must not be null", u);
        GraphChecker.checkNull("Edge must not be null", e);

        GraphChecker.checkPresence("Node must be present in the graph", this, u);

        if (incidenceMatrix.get(u).get(e) == EdgeDirection.NO_EDGE) {
            return null;
        }

        for (Node<V> node : incidenceMatrix.keySet()) {
            if (incidenceMatrix.get(node).get(e) == EdgeDirection.INGOING) {
                return node;
            }
        }

        return null;
    }

    @Override
    public List<Edge<W>> getEdges() {
        if (getVertexCount() > 0) {
            Set<Edge<W>> edges = new HashSet<>();

            for (Node<V> node : incidenceMatrix.keySet()) {
                edges = incidenceMatrix.get(node).keySet();
                break;
            }

            return new ArrayList<>(edges);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean removeEdge(Node<V> u, Node<V> v) {

        GraphChecker.checkNull("Nodes must not be null", u, v);
        GraphChecker.checkPresence("Nodes must be present in the graph", this, u, v);

        Edge<W> newEdge = getEdge(u, v);

        if (newEdge == null) {
            return false;
        }

        incidenceMatrix.get(u).put(newEdge, EdgeDirection.NO_EDGE);
        incidenceMatrix.get(v).put(newEdge, EdgeDirection.NO_EDGE);

        return true;
    }

    @Override
    public Node<V> addVertex(V value) {
        Node<V> newNode = new Node<>(value);

        incidenceMatrix.put(newNode, new HashMap<>());

        if (incidenceMatrix.size() > 1) {
            Set<Edge<W>> edges = new HashSet<>();

            for (Node<V> node : incidenceMatrix.keySet()) {
                if (!node.equals(newNode)) {
                    edges = incidenceMatrix.get(node).keySet();
                    break;
                }
            }

            for (Edge<W> edge : edges) {
                incidenceMatrix.get(newNode).put(edge, EdgeDirection.NO_EDGE);
            }
        }

        return newNode;
    }

    @Override
    public List<Node<V>> getVertices() {
        return new ArrayList<>(incidenceMatrix.keySet());
    }

    @Override
    public boolean removeVertex(Node<V> node) {
        GraphChecker.checkNull("Node must not be null", node);

        if (incidenceMatrix.get(node) == null) {
            return false;
        }

        incidenceMatrix.remove(node);

        return true;
    }

    @Override
    public Node<V> find(V value) {
        for (Node<V> node : incidenceMatrix.keySet()) {
            if (node.getValue().equals(value)) {
                return node;
            }
        }

        return null;
    }

    @Override
    public int getVertexCount() {
        return incidenceMatrix.keySet().size();
    }

    @Override
    public int getEdgesCount() {
        return getEdges().size();
    }
}
