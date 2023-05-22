package ru.nsu.nrepin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.nrepin.model.Cell;
import ru.nsu.nrepin.model.GameField;
import ru.nsu.nrepin.model.Snake;

/**
 * Tests for some game modules.
 */
public class GameTests {

    /**
     * Test Snake class.
     */
    @Test
    public void testSnake() {
        GameField field = new GameField(10, 10);
        Snake snake = new Snake(5, 6, field);

        Vector2 expectedHeadPos = new Vector2(5, 5);
        Vector2 nextHeadPos = snake.nextHeadPosition(Direction.UP);

        snake.move(Direction.UP, false);

        Vector2 actualHeadPos = snake.getHeadPosition();

        Assertions.assertEquals(expectedHeadPos, nextHeadPos);
        Assertions.assertEquals(expectedHeadPos, actualHeadPos);

        Assertions.assertEquals(0, snake.getTailPositions().size());

        snake.move(Direction.LEFT, true);

        Assertions.assertEquals(1, snake.getTailPositions().size());

        snake = new Snake(3, 4, field);

        field.setCell(Cell.FOOD, 3, 3);

        Assertions.assertEquals(Cell.FOOD, snake.nextCell(Direction.UP));
    }

    /**
     * Test GameField class.
     */
    @Test
    public void testGameField() {
        GameField field = new GameField(5, 7);

        Assertions.assertEquals(5, field.getRowsCount());
        Assertions.assertEquals(7, field.getColsCount());

        field.setCell(Cell.SNAKE_TAIL, 2, 1);
        field.setCell(Cell.SNAKE_HEAD, new Vector2(3, 4));

        Assertions.assertEquals(Cell.SNAKE_TAIL, field.getCell(2, 1));
        Assertions.assertEquals(Cell.SNAKE_HEAD, field.getCell(new Vector2(3, 4)));
    }
}
