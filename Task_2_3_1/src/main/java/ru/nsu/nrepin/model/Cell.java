package ru.nsu.nrepin.model;

/**
 * A type that game field cell may have.
 */
public enum Cell {
    /**
     * Empty cell.
     */
    EMPTY,
    /**
     * Snake's head.
     */
    SNAKE_HEAD,
    /**
     * Snake's tail.
     */
    SNAKE_TAIL,
    /**
     * Food cell.
     */
    FOOD,
    /**
     * Wall.
     */
    WALL
}
