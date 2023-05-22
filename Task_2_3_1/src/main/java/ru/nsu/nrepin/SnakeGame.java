package ru.nsu.nrepin;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class of SnakeGame.
 */
public class SnakeGame extends Application {

    private static final int FIELD_SIZE = 10;
    private static final int GAME_DELAY = 125;

    /**
     * Program entry point.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameView view = new GameView(
                410,
                410,
                "Snake Game",
                primaryStage,
                FIELD_SIZE, FIELD_SIZE
        );
        GameModel model = new GameModel(FIELD_SIZE, FIELD_SIZE);
        GameController controller = new GameController(model, view);
        ControlsHandler controlsHandler = new ControlsHandler(controller);

        GameThread gameThread = new GameThread(GAME_DELAY, controller);
        controller.setGameThread(gameThread);

        controller.startGame();

        view.setKeyPressedHandler(controlsHandler);
        view.setCloseHandler(event -> gameThread.interrupt());
    }
}