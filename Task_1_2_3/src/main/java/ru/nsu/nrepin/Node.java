package ru.nsu.nrepin;

/**
 * Class that represents vertex of a graph.
 *
 * @param <V> type of value stored in graph node
 */
public class Node<V> {
    private V value;

    /**
     * Creates new node with a given value.
     *
     * @param value new node value
     */
    public Node(V value) {
        this.value = value;
    }

    /**
     * Returns current node value.
     *
     * @return current node value
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets a value of current node to some new value.
     *
     * @param value new value
     */
    public void setValue(V value) {
        this.value = value;
    }
}
