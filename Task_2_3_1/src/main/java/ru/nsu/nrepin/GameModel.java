package ru.nsu.nrepin;

public class GameModel {

    private static final int INITIAL_LENGTH = 2;

    private GameField field;
    private Snake snake;
    private final GameView gameView;
    private final FoodGenerator foodGenerator;

    private int score = INITIAL_LENGTH;

    public GameModel(int fieldCols, int fieldRows, GameView view) {
        field = new GameField(fieldCols, fieldRows);
        snake = new Snake(fieldCols / 2, fieldRows / 2, field);
        gameView = view;

        foodGenerator = new FoodGenerator(this);

        foodGenerator.generateFood();
    }

    public GameStatus step(Direction direction) {

        boolean grow = false;

        GameStatus status = GameStatus.PLAYING;

        Cell nextCell = snake.nextCell(direction);

        switch (nextCell) {
            case SNAKE_TAIL:
            case SNAKE_HEAD:
                gameView.showLoseLabel(score);
                status = GameStatus.LOSE;
                break;
            case FOOD:
                grow = true;
                break;
        }

        clearSnake();
        snake.move(direction, grow);
        applySnake();

        if (grow) {
            score++;

            if (score == field.getRowsCount() * field.getColsCount()) {
                gameView.showWinLabel();
                status = GameStatus.WIN;
            } else {
                foodGenerator.generateFood();
            }
        }

        gameView.draw(this);

        return status;
    }

    public GameField getField() {
        return field;
    }

    public int getScore() {
        return score;
    }

    public void resetGame() {
        score = INITIAL_LENGTH;

        field = new GameField(field.getColsCount(), field.getRowsCount());
        snake = new Snake(field.getColsCount() / 2, field.getRowsCount() / 2, field);

        gameView.hideLabels();

        foodGenerator.generateFood();
    }

    public GameView getView() {
        return gameView;
    }

    private void clearSnake() {
        for (int i = 0; i < field.getRowsCount(); ++i) {
            for (int j = 0; j < field.getColsCount(); ++j) {
                if (field.getCell(j, i) == Cell.SNAKE_HEAD ||
                        field.getCell(j, i) == Cell.SNAKE_TAIL) {
                    field.setCell(Cell.EMPTY, j, i);
                }
            }
        }
    }

    private void applySnake() {
        field.setCell(Cell.SNAKE_HEAD, snake.getHeadPosition());

        for (Vector2 pos : snake.getTailPositions()) {
            field.setCell(Cell.SNAKE_TAIL, pos);
        }
    }
}
