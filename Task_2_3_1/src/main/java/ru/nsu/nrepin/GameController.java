package ru.nsu.nrepin;

public class GameController {

    private GameThread gameThread;

    private final GameModel gameModel;

    private Direction direction = Direction.UP;

    private boolean paused = false;

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void setGameThread(GameThread thread) {
        gameThread = thread;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void togglePause() {
        paused = !paused;
        gameModel.getView().togglePauseLabel();
    }

    public void showHelp() {
        gameModel.getView().showHelp();
    }

    public void step() {
        if (!paused) {
            switch (gameModel.step(direction)) {
                case WIN:
                    winGame();
                    break;
                case LOSE:
                    loseGame();
                    break;
            }
        }
    }

    public void startGame() {
        gameThread.start();
    }

    public void resetGame() {
        gameModel.resetGame();
        gameThread.resumeGame();
    }

    public void winGame() {
        gameThread.stopGame();
    }

    public void loseGame() {
        gameThread.stopGame();
    }
}
