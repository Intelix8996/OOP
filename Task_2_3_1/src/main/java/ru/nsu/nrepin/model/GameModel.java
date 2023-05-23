package ru.nsu.nrepin.model;

import ru.nsu.nrepin.Direction;
import ru.nsu.nrepin.Vector2;

/**
 * Model class for SnakeGame.
 */
public class GameModel {

    private static final int INITIAL_LENGTH = 1;
    private static final int WALL_COUNT_PERCENT = 5;
    private static final int FOOD_COUNT_PERCENT = 3;
    private static final int LENGTH_TO_WIN_PERCENT = 100;

    private final int lengthToWin;
    private final int foodCount;

    private GameField field;
    private Snake snake;
    private EmptyCellGenerator cellGenerator;

    private int score = INITIAL_LENGTH;

    /**
     * Creates new GameModel.
     *
     * @param fieldCols column count
     * @param fieldRows row count
     */
    public GameModel(int fieldCols, int fieldRows) {
        field = new GameField(fieldCols, fieldRows);
        snake = new Snake(fieldCols / 2, fieldRows / 2, field);

        int cellsCount = field.getRowsCount() * field.getColsCount();
        int wallsCount = cellsCount * WALL_COUNT_PERCENT / 100;

        foodCount = cellsCount * FOOD_COUNT_PERCENT / 100;

        lengthToWin = (cellsCount - wallsCount - foodCount)
                * LENGTH_TO_WIN_PERCENT / 100;

        cellGenerator = new EmptyCellGenerator(field);

        generateFood(foodCount);
        generateWalls();
    }

    /**
     * Performs one step of a game.
     *
     * @param direction current move direction
     * @return status of a game after step
     */
    public GameStatus step(Direction direction) {

        boolean grow = false;

        GameStatus status = GameStatus.PLAYING;

        Cell nextCell = snake.nextCell(direction);

        switch (nextCell) {
            case WALL:
            case SNAKE_TAIL:
            case SNAKE_HEAD:
                status = GameStatus.LOSE;
                break;
            case FOOD:
                grow = true;
                break;
            default:
                break;
        }

        clearSnake();
        snake.move(direction, grow);
        applySnake();

        if (grow) {
            score++;

            if (score == lengthToWin) {
                status = GameStatus.WIN;
            } else {
                generateFood(1);
            }
        }

        return status;
    }

    /**
     * Returns assigned GameField.
     *
     * @return game field
     */
    public GameField getField() {
        return field;
    }

    /**
     * Returns current score.
     *
     * @return current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Resets game.
     */
    public void resetGame() {
        score = INITIAL_LENGTH;

        field = new GameField(field.getColsCount(), field.getRowsCount());
        snake = new Snake(field.getColsCount() / 2, field.getRowsCount() / 2, field);

        cellGenerator = new EmptyCellGenerator(field);

        generateFood(foodCount);
        generateWalls();
    }

    /**
     * Clears snake cells on the field.
     */
    private void clearSnake() {
        for (int i = 0; i < field.getRowsCount(); ++i) {
            for (int j = 0; j < field.getColsCount(); ++j) {
                if (field.getCell(j, i) == Cell.SNAKE_HEAD
                        || field.getCell(j, i) == Cell.SNAKE_TAIL) {
                    field.setCell(Cell.EMPTY, j, i);
                }
            }
        }
    }

    /**
     * Sets snake cells on the field according to snake's pieces coordinates.
     */
    private void applySnake() {
        field.setCell(Cell.SNAKE_HEAD, snake.getHeadPosition());

        for (Vector2 pos : snake.getTailPositions()) {
            field.setCell(Cell.SNAKE_TAIL, pos);
        }
    }

    private void generateFood(int n) {
        for (int i = 0; i < n; ++i) {
            field.setCell(Cell.FOOD, cellGenerator.getEmptyCell());
        }
    }

    private void generateWalls() {

        int wallCount = field.getRowsCount() * field.getColsCount() * WALL_COUNT_PERCENT / 100;

        for (int i = 0; i < wallCount; ++i) {
            field.setCell(Cell.WALL, cellGenerator.getEmptyCell());
        }
    }
}
