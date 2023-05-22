package ru.nsu.nrepin.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.nsu.nrepin.Direction;

/**
 * Class that handles key events.
 */
public class ControlsHandler implements EventHandler<KeyEvent> {

    private final GameController gameController;

    /**
     * Creates new handler and assigns a GameController to it.
     *
     * @param gameController GameController to assign
     */
    public ControlsHandler(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void handle(KeyEvent event) {
        KeyCode key = event.getCode();

        switch (key) {
            case W:
                gameController.setDirection(Direction.UP);
                break;
            case S:
                gameController.setDirection(Direction.DOWN);
                break;
            case A:
                gameController.setDirection(Direction.LEFT);
                break;
            case D:
                gameController.setDirection(Direction.RIGHT);
                break;
            case P:
                gameController.togglePause();
                break;
            case R:
                gameController.resetGame();
                break;
            case H:
                gameController.showHelp();
                break;
            default:
                break;
        }
    }
}
