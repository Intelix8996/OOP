package ru.nsu.nrepin.controller;

import ru.nsu.nrepin.Direction;
import ru.nsu.nrepin.view.GameView;
import ru.nsu.nrepin.model.GameModel;

/**
 * Controller class for SnakeGame.
 */
public class GameController {

    private static final int GAME_DELAY = 125;

    private GameThread gameThread;
    private final GameModel gameModel;
    private final GameView gameView;

    private Direction direction = Direction.UP;

    private boolean paused = false;

    /**
     * Creates new game controller and assigns GameModel to it.
     *
     * @param gameModel GameModel to assign
     */
    public GameController(GameModel gameModel, GameView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;

        ControlsHandler controlsHandler = new ControlsHandler(this);

        gameThread = new GameThread(GAME_DELAY, this);

        gameView.setKeyPressedHandler(controlsHandler);
        gameView.setCloseHandler(event -> gameThread.interrupt());
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
        gameView.togglePauseLabel();
    }

    /**
     * Shows help window.
     */
    public void showHelp() {
        gameView.showHelp();
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
                default:
                    gameView.draw(gameModel);
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
        gameView.hideLabels();
        gameModel.resetGame();
        gameThread.resumeGame();
    }

    /**
     * Callback for game win.
     */
    public void winGame() {
        gameView.showWinLabel();
        gameThread.stopGame();
    }

    /**
     * Callback for game lose.
     */
    public void loseGame() {
        gameView.showLoseLabel(gameModel.getScore());
        gameThread.stopGame();
    }
}
