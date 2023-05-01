package ru.nsu.nrepin;

import java.util.Random;

public class FoodGenerator {

    private final GameModel gameModel;

    public FoodGenerator(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void generateFood() {
        GameField field = gameModel.getField();

        Random random = new Random();

        while (true) {
            int x = Math.abs(random.nextInt());
            int y = Math.abs(random.nextInt());

            x %= field.getColsCount();
            y %= field.getRowsCount();

            if (field.getCell(x, y) == Cell.EMPTY) {
                field.setCell(Cell.FOOD, x, y);
                break;
            }
        }
    }
}
