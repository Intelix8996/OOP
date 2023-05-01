package ru.nsu.nrepin;


import java.util.ArrayDeque;
import java.util.Queue;

public class Snake {

    private final GameField gameField;

    private final Vector2 headPosition;
    private final Queue<Vector2> tailPositions = new ArrayDeque<>();

    public Snake(int x, int y, GameField field) {
        headPosition = new Vector2(x, y);
        tailPositions.add(new Vector2(5, 8));
        gameField = field;
    }

    public void move(Direction direction, boolean grow) {
        if (!grow) {
            tailPositions.poll();
        }

        tailPositions.add(new Vector2(headPosition));

        headPosition.add(direction.toVector());
        headPosition.mod(gameField.getColsCount(), gameField.getRowsCount());
    }

    public Vector2 nextHeadPosition(Direction direction) {
        return Vector2.add(headPosition, direction.toVector());
    }

    public Cell nextCell(Direction direction) {
        Vector2 nextHeadPosition = nextHeadPosition(direction);
        nextHeadPosition.mod(gameField.getColsCount(), gameField.getRowsCount());

        return gameField.getCell(nextHeadPosition);
    }

    public Vector2 getHeadPosition() {
        return headPosition;
    }

    public Queue<Vector2> getTailPositions() {
        return tailPositions;
    }
}
