package ru.nsu.nrepin;


import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Class that represents snake.
 */
public class Snake {

    private final GameField gameField;

    private final Vector2 headPosition;
    private final Queue<Vector2> tailPositions = new ArrayDeque<>();

    /**
     * Creates new snake with initial position.
     *
     * @param x initial x coordinate
     * @param y initial y coordinate
     * @param field assigned GameField
     */
    public Snake(int x, int y, GameField field) {
        headPosition = new Vector2(x, y);
        tailPositions.add(new Vector2(5, 8));
        gameField = field;
    }

    /**
     * Moves snake in given direction and increases its length if {@code grow} is {@code true}.
     *
     * @param direction move direction
     * @param grow should grow
     */
    public void move(Direction direction, boolean grow) {
        if (!grow) {
            tailPositions.poll();
        }

        tailPositions.add(new Vector2(headPosition));

        headPosition.add(direction.toVector());
        headPosition.mod(gameField.getColsCount(), gameField.getRowsCount());
    }

    /**
     * Returns head position after move in given direction, but doesn't modify it.
     *
     * @param direction move direction
     * @return new head position
     */
    public Vector2 nextHeadPosition(Direction direction) {
        return Vector2.add(headPosition, direction.toVector());
    }

    /**
     * Returns next cell that snake will hit if it moves in given direction,
     * doesn't modify snake's position.
     *
     * @param direction move direction
     * @return next cell type
     */
    public Cell nextCell(Direction direction) {
        Vector2 nextHeadPosition = nextHeadPosition(direction);
        nextHeadPosition.mod(gameField.getColsCount(), gameField.getRowsCount());

        return gameField.getCell(nextHeadPosition);
    }

    /**
     * Returns current position of snake's head.
     *
     * @return snake's head position
     */
    public Vector2 getHeadPosition() {
        return headPosition;
    }

    /**
     * Returns current positions of all snake's tail parts.
     *
     * @return positions of all snake's tail parts
     */
    public Queue<Vector2> getTailPositions() {
        return tailPositions;
    }
}
