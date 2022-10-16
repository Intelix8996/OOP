package ru.nsu.nrepin;

import java.util.List;

public interface Graph<V, W extends Number> {

    Edge<W> addEdge(Node<V> u, Node<V> v, W weight);
    Edge<W> getEdge(Node<V> u, Node<V> v);
    List<Edge<W>> getNodeEdges(Node<V> u);
    Node<V> getEdgeTerminus(Node<V> u, Edge<W> e);
    List<Edge<W>> getEdges();
    boolean removeEdge(Node<V> u, Node<V> v);

    Node<V> addVertex(V value);
    List<Node<V>> getVertices();
    boolean removeVertex(Node<V> node);

    Node<V> find(V value);

    int getVertexCount();
    int getEdgesCount();

}
