package ru.nsu.nrepin.model;

import ru.nsu.nrepin.Vector2;

import java.util.Random;

public class EmptyCellGenerator {

    private final GameField field;

    public EmptyCellGenerator(GameField field) {
        this.field = field;
    }

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
