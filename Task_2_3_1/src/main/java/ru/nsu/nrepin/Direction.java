package ru.nsu.nrepin;

/**
 * Directions of movement with vector representation.
 */
public enum Direction {
    /**
     * Up.
     */
    UP(new Vector2(0, -1)),
    /**
     * Down.
     */
    DOWN(new Vector2(0, 1)),
    /**
     * Left.
     */
    LEFT(new Vector2(-1, 0)),
    /**
     * Right.
     */
    RIGHT(new Vector2(1, 0));

    private final Vector2 vector;

    /**
     * Creates new enum member.
     *
     * @param vector vector representation
     */
    Direction(Vector2 vector) {
        this.vector = vector;
    }

    /**
     * Return direction as vector.
     *
     * @return direction as vector
     */
    public Vector2 toVector() {
        return vector;
    }
}
