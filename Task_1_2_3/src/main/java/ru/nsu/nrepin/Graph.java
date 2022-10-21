package ru.nsu.nrepin;

import java.util.List;

/**
 * This interface specifies behaviour for directed weighted graphs.
 * <i>NOTE: Terms <b>Node</b> and <b>Vertex</b> can be used interchangeably in this context.<i/>
 * <i>Vertices are represented with {@code Node} class.</i>
 *
 * @param <V> type of value stored in graph node
 * @param <W> type of edge weight
 */
public interface Graph<V, W extends Number> {

    /**
     * Add edge between two nodes.
     *
     * @param u origin node
     * @param v terminus node
     * @param weight weight of edge
     * @return new {@code Edge} object
     */
    Edge<W> addEdge(Node<V> u, Node<V> v, W weight);

    /**
     * Get edge between two nodes if present, otherwise return {@code null}.
     *
     * @param u origin node
     * @param v terminus node
     * @return {@code} Edge object if edge is present, {@code null} otherwise
     */
    Edge<W> getEdge(Node<V> u, Node<V> v);

    /**
     * Get all outgoing edges of a node as a {@code List}.
     *
     * @param u node
     * @return list of edges
     */
    List<Edge<W>> getNodeEdges(Node<V> u);

    /**
     * Get edge terminus node by origin node and edge itself.
     *
     * @param u origin node
     * @param e edge
     * @return terminus node
     */
    Node<V> getEdgeTerminus(Node<V> u, Edge<W> e);

    /**
     * Get all edges present in graph.
     *
     * @return list of all edges
     */
    List<Edge<W>> getEdges();

    /**
     * Remove edge between nodes <i>u</i> and <i>v</i>.
     *
     * @param u edge origin
     * @param v edge terminus
     * @return {@code true} if operation was successful, {@code false} otherwise
     */
    boolean removeEdge(Node<V> u, Node<V> v);

    /**
     * Add new vertex to graph.
     *
     * @param value value of new vertex
     * @return new {@code Node} object
     */
    Node<V> addVertex(V value);

    /**
     * Get all vertices present in graph.
     *
     * @return list of all graph vertices
     */
    List<Node<V>> getVertices();

    /**
     * Remove vertex from graph.
     *
     * @param node vertex to be removed
     * @return {@code true} if operation was successful, {@code false} otherwise
     */
    boolean removeVertex(Node<V> node);

    /**
     * Find vertex by value.
     *
     * @param value value to be searched for
     * @return {@code Node} if such node was found, {@code null} otherwise
     */
    Node<V> find(V value);

    /**
     * Get the number of vertices present in the graph.
     *
     * @return number of vertices in the graph
     */
    int getVertexCount();

    /**
     * Get the number of edges present in the graph.
     *
     * @return number of edges in the graph
     */
    int getEdgesCount();

}
