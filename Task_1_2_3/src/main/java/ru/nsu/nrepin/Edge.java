package ru.nsu.nrepin;

public class Edge<W extends Number> {
    private W weight;

    public Edge(W weight) {
        this.weight = weight;
    }

    public W getWeight() {
        return weight;
    }

    public void setWeight(W weight) {
        this.weight = weight;
    }
}
