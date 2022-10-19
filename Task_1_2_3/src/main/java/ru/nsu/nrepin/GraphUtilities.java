package ru.nsu.nrepin;

import java.util.*;

public class GraphUtilities {

    @SuppressWarnings("unchecked")
    public static <V, W extends Number> Map<Node<V>, W> Dijkstra(Graph<V, W> graph, Node<V> origin) {

        Set<Node<V>> visited = new HashSet<>();
        Map<Node<V>, W> distances = new HashMap<>();

        for (var vertex : graph.getVertices()) {
            distances.put(vertex, (W)Double.valueOf(Double.POSITIVE_INFINITY));
        }

        distances.put(origin, (W)Integer.valueOf(0));

        for (var i : graph.getVertices()) {
            Node<V> node = null;

            for (var j : graph.getVertices()) {
                if (!visited.contains(j) &&
                        (node == null || distances.get(j).doubleValue() < distances.get(node).doubleValue())) {
                    node = j;
                }
            }

            if (distances.get(node).equals(Double.POSITIVE_INFINITY)) {
                break;
            }

            visited.add(node);

            for (var edge : graph.getNodeEdges(node)) {
                Node<V> terminus = graph.getEdgeTerminus(node, edge);

                W currentDistance = distances.get(node);
                W terminusDistance = distances.get(terminus);
                W edgeWeight = edge.getWeight();

                if (currentDistance.doubleValue() + edgeWeight.doubleValue() < terminusDistance.doubleValue()) {
                    distances.replace(
                            terminus,
                            (W)Double.valueOf(currentDistance.doubleValue() + edgeWeight.doubleValue())
                    );
                }
            }
        }

        return distances;
    }
}
