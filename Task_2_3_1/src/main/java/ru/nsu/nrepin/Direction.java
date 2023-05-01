package ru.nsu.nrepin;

public enum Direction {
    UP(new Vector2(0, -1)),
    DOWN(new Vector2(0, 1)),
    LEFT(new Vector2(-1, 0)),
    RIGHT(new Vector2(1, 0));

    private final Vector2 vector;

    Direction(Vector2 vector) {
        this.vector = vector;
    }

    public Vector2 toVector() {
        return vector;
    }
}
