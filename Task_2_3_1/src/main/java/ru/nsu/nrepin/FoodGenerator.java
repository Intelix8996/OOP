package ru.nsu.nrepin;

import java.util.Random;

/**
 * Class that generates food on game field.
 */
public class FoodGenerator {

    private final GameModel gameModel;

    /**
     * Creates new FoodGenerator and assigns GameModel to it.
     *
     * @param gameModel GameModel to assign
     */
    public FoodGenerator(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Generates food in random empty cell of game field.
     */
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
