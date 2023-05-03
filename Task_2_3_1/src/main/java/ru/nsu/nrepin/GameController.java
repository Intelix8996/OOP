package ru.nsu.nrepin;

/**
 * Controller class for SnakeGame.
 */
public class GameController {

    private GameThread gameThread;

    private final GameModel gameModel;

    private Direction direction = Direction.UP;

    private boolean paused = false;

    /**
     * Creates new game controller and assigns GameModel to it.
     *
     * @param gameModel GameModel to assign
     */
    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Sets GameThread for this controller.
     *
     * @param thread GameThread to set
     */
    public void setGameThread(GameThread thread) {
        gameThread = thread;
    }

    /**
     * Sets direction of movement.
     *
     * @param direction new direction of movement
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Toggles game pause.
     */
    public void togglePause() {
        paused = !paused;
        gameModel.getView().togglePauseLabel();
    }

    /**
     * Shows help window.
     */
    public void showHelp() {
        gameModel.getView().showHelp();
    }

    /**
     * Performs one step of the game.
     */
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

    /**
     * Starts game.
     */
    public void startGame() {
        gameThread.start();
    }

    /**
     * Resets game.
     */
    public void resetGame() {
        gameModel.resetGame();
        gameThread.resumeGame();
    }

    /**
     * Callback for game win.
     */
    public void winGame() {
        gameThread.stopGame();
    }

    /**
     * Callback for game lose.
     */
    public void loseGame() {
        gameThread.stopGame();
    }
}
