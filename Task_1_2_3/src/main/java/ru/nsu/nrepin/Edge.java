package ru.nsu.nrepin;

/**
 * This class represents weighted edge of a graph.
 *
 * @param <W> type of edge weight
 */
public class Edge<W extends Number> {
    private W weight;

    /**
     * Creates new edge with given weight.
     *
     * @param weight weight of an edge
     */
    public Edge(W weight) {
        this.weight = weight;
    }

    /**
     * Returns weight of a current edge.
     *
     * @return weight of a cureent edge.
     */
    public W getWeight() {
        return weight;
    }

    /**
     * Sets weight of current edge.
     *
     * @param weight new weight
     */
    public void setWeight(W weight) {
        this.weight = weight;
    }
}
