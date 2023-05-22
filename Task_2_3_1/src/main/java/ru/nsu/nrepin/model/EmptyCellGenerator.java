package ru.nsu.nrepin.model;

import ru.nsu.nrepin.Vector2;

import java.util.Random;

/**
 * Class that is responsible for selecting empty cells.
 */
public class EmptyCellGenerator {

    private final GameField field;

    /**
     * Creates new generator for given field.
     *
     * @param field assigned field
     */
    public EmptyCellGenerator(GameField field) {
        this.field = field;
    }

    /**
     * Returns coordinates of some empty cell.
     *
     * @return coordinates of some empty cell
     */
    public Vector2 getEmptyCell() {
        Random random = new Random();

        while (true) {
            int x = Math.abs(random.nextInt());
            int y = Math.abs(random.nextInt());

            x %= field.getColsCount();
            y %= field.getRowsCount();

            if (field.getCell(x, y) == Cell.EMPTY) {
                return new Vector2(x, y);
            }
        }
    }
}
