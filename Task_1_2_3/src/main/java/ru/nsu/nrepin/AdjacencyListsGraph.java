package ru.nsu.nrepin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements graph with adjacency list representation.
 *
 * @param <V> type of value stored in graph node
 * @param <W> type of edge weight
 */
public class AdjacencyListsGraph<V, W extends Number> implements Graph<V, W> {

    private final Map<Node<V>, List<Pair<Node<V>, Edge<W>>>> nodes;

    /**
     * Constructs new empty graph.
     */
    public AdjacencyListsGraph() {
        nodes = new HashMap<>();
    }

    @Override
    public Edge<W> addEdge(Node<V> u, Node<V> v, W weight) {
        Edge<W> newEdge = new Edge<>(weight);

        nodes.get(u).add(new Pair<>(v, newEdge));

        return newEdge;
    }

    @Override
    public Edge<W> getEdge(Node<V> u, Node<V> v) {
        for (var pair : nodes.get(u)) {
            if (pair.getLeft().equals(v)) {
                return pair.getRight();
            }
        }

        return null;
    }

    @Override
    public List<Edge<W>> getNodeEdges(Node<V> u) {

        List<Edge<W>> edges = new ArrayList<>();

        for (var pair : nodes.get(u)) {
            edges.add(pair.getRight());
        }

        return edges;
    }

    @Override
    public Node<V> getEdgeTerminus(Node<V> u, Edge<W> e) {
        for (var pair : nodes.get(u)) {
            if (pair.getRight().equals(e)) {
                return pair.getLeft();
            }
        }

        return null;
    }

    @Override
    public List<Edge<W>> getEdges() {
        List<Edge<W>> edges = new ArrayList<>();

        for (var node : nodes.keySet()) {
            for (var pair : nodes.get(node)) {
                edges.add(pair.getRight());
            }
        }

        return edges;
    }

    @Override
    public boolean removeEdge(Node<V> u, Node<V> v) {
        for (var pair : nodes.get(u)) {
            if (pair.getLeft().equals(v)) {
                nodes.get(u).remove(pair);
                return true;
            }
        }
        return false;
    }

    @Override
    public Node<V> addVertex(V value) {
        Node<V> newNode = new Node<>(value);

        nodes.put(newNode, new ArrayList<>());

        return newNode;
    }

    @Override
    public List<Node<V>> getVertices() {
        return nodes.keySet().stream().toList();
    }

    @Override
    public boolean removeVertex(Node<V> node) {
        if (!nodes.containsKey(node)) {
            return false;
        }

        nodes.remove(node);

        return true;
    }

    @Override
    public Node<V> find(V value) {
        for (var node : nodes.keySet()) {
            if (node.getValue().equals(value)) {
                return node;
            }
        }

        return null;
    }

    @Override
    public int getVertexCount() {
        return nodes.keySet().size();
    }

    @Override
    public int getEdgesCount() {

        int edgesCount = 0;

        for (var node : nodes.keySet()) {
            edgesCount += nodes.get(node).size();
        }

        return edgesCount;
    }
}