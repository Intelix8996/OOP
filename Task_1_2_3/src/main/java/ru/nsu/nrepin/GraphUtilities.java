package ru.nsu.nrepin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class contains algorithms that can be applied to {@code Graph}.
 */
public class GraphUtilities {

    /**
     * Runs Dijkstra's algorithms on a given graph from a given node.
     *
     * @param graph graph to run algorithm on
     * @param origin starting vertex
     * @param <V> type of value stored in graph node
     * @param <W> type of edge weight
     * @return mapping of nodes to their distances from {@code origin} vertex
     */
    public static <V, W extends Number> Map<Node<V>, Double> dijkstra(
            Graph<V, W> graph,
            Node<V> origin)
    {

        Set<Node<V>> visited = new HashSet<>();
        Map<Node<V>, Double> distances = new HashMap<>();

        for (var vertex : graph.getVertices()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
        }

        distances.put(origin, 0.0);

        for (var i : graph.getVertices()) {
            Node<V> node = null;

            for (var j : graph.getVertices()) {
                if (!visited.contains(j)
                        && (node == null || distances.get(j) < distances.get(node))) {
                    node = j;
                }
            }

            if (distances.get(node).equals(Double.POSITIVE_INFINITY)) {
                break;
            }

            visited.add(node);

            for (var edge : graph.getNodeEdges(node)) {
                Node<V> terminus = graph.getEdgeTerminus(node, edge);

                Double currentDistance = distances.get(node);
                Double terminusDistance = distances.get(terminus);
                Double edgeWeight = edge.getWeight().doubleValue();

                if (currentDistance + edgeWeight < terminusDistance) {
                    distances.replace(terminus, currentDistance + edgeWeight);
                }
            }
        }

        return distances;
    }
}
