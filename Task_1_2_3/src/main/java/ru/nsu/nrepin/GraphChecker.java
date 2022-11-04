package ru.nsu.nrepin;

/**
 * This class is used to perform {@code null} checks.
 */
public class GraphChecker {
    /**
     * Checks if any of given values are {@code null}.
     * If it is true throws {@code IllegalStateException} with given message.
     *
     * @param msg message that will be passed to exception
     * @param values values to check
     * @param <T> type of values
     */
    @SafeVarargs
    public static <T> void checkNull(String msg, T... values) {
        for (T value : values) {
            if (value == null) {
                throw new IllegalStateException(msg);
            }
        }
    }

    /**
     * Checks if all given nodes are present in the given graph.
     * Otherwise, throw {@code IllegalStateException} with given message.
     *
     * @param msg message that will be passed to exception
     * @param graph graph to check presence in
     * @param nodes nodes to check
     * @param <V> node value type
     * @param <W> edge weight type
     */
    @SafeVarargs
    public static <V, W extends Number> void checkPresence(String msg,
                                                           Graph<V, W> graph,
                                                           Node<V>... nodes) {
        for (Node<V> node : nodes) {
            if (!graph.contains(node)) {
                throw new IllegalStateException(msg);
            }
        }
    }

    /**
     * Checks if all given edges are present in the given graph.
     * Otherwise, throw {@code IllegalStateException} with given message.
     *
     * @param msg message that will be passed to exception
     * @param graph graph to check presence in
     * @param edges edges to check
     * @param <V> node value type
     * @param <W> edge weight type
     */
    @SafeVarargs
    public static <V, W extends Number> void checkPresence(String msg,
                                                           Graph<V, W> graph,
                                                           Edge<W>... edges) {
        for (Edge<W> edge : edges) {
            if (!graph.contains(edge)) {
                throw new IllegalStateException(msg);
            }
        }
    }
}
