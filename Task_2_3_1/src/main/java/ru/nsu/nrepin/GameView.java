package ru.nsu.nrepin;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * View class for SnakeGame.
 */
public class GameView {

    private static final int CELL_SIZE = 40;

    private final Scene mainScene;

    private final Stage mainStage;

    private final Label scoreLabel;
    private final Label winGameLabel;
    private final Label loseGameLabel;
    private final Label pauseLabel;

    private final List<List<Rectangle>> cellRectangles = new ArrayList<>();

    /**
     * Creates new game view with given parameters.
     *
     * @param windowWidth width of the window
     * @param windowHeight height of the window
     * @param windowTitle title of the window
     * @param primaryStage primary stage
     * @param fieldCols columns count of the field
     * @param fieldRows rows count of the field
     */
    public GameView(
            int windowWidth, int windowHeight, String windowTitle, Stage primaryStage,
            int fieldCols, int fieldRows) {
        Pane root = new Pane();
        mainScene = new Scene(root, windowWidth, windowHeight);
        mainStage = primaryStage;

        GridPane gridPane = new GridPane();

        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        primaryStage.setMinHeight(windowHeight);
        primaryStage.setMaxHeight(windowHeight + 1);
        primaryStage.setMinWidth(windowWidth);
        primaryStage.setMaxWidth(windowWidth + 1);

        for (int i = 0; i < fieldRows; ++i) {
            cellRectangles.add(new ArrayList<>());
            for (int j = 0; j < fieldCols; ++j) {
                Rectangle newRect = new Rectangle(CELL_SIZE, CELL_SIZE);
                newRect.setFill(Colors.BACKGROUND);

                gridPane.add(newRect, j, i);
                cellRectangles.get(i).add(newRect);
            }
        }

        root.getChildren().add(gridPane);

        scoreLabel = new Label("Score: ");

        root.getChildren().add(scoreLabel);

        winGameLabel = new Label("Win!");
        winGameLabel.setFont(Font.font("Cambria", 32));
        winGameLabel.setVisible(false);
        winGameLabel.setAlignment(Pos.CENTER);
        winGameLabel.setPadding(new Insets(160));

        root.getChildren().add(winGameLabel);

        loseGameLabel = new Label("Lose!");
        loseGameLabel.setFont(Font.font("Cambria", 32));
        loseGameLabel.setVisible(false);
        loseGameLabel.setAlignment(Pos.CENTER);
        loseGameLabel.setPadding(new Insets(160));

        root.getChildren().add(loseGameLabel);

        pauseLabel = new Label("Pause");
        pauseLabel.setFont(Font.font("Cambria", 32));
        pauseLabel.setVisible(false);
        pauseLabel.setAlignment(Pos.CENTER);
        pauseLabel.setPadding(new Insets(160));

        root.getChildren().add(pauseLabel);

        Label helpLabel = new Label("Press H for help");
        helpLabel.setPadding(new Insets(0, 0, 0, CELL_SIZE * fieldCols - 75));

        root.getChildren().add(helpLabel);

        primaryStage.setTitle(windowTitle);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    /**
     * Sets handler for KeyPressed event.
     *
     * @param handler handler
     */
    public void setKeyPressedHandler(EventHandler<? super KeyEvent> handler) {
        mainScene.setOnKeyPressed(handler);
    }

    /**
     * Sets handler for OnClose event.
     *
     * @param handler handler
     */
    public void setCloseHandler(EventHandler<WindowEvent> handler) {
        mainStage.setOnCloseRequest(handler);
    }

    /**
     * Shows "Win" label.
     */
    public void showWinLabel() {
        winGameLabel.setVisible(true);
    }

    /**
     * Shows "Lose" label with score.
     *
     * @param score current score
     */
    public void showLoseLabel(int score) {
        Platform.runLater(() -> loseGameLabel.setText("Lose!\nLength: " + score));
        loseGameLabel.setVisible(true);
    }

    /**
     * Hides "Win" and "Lose" labels.
     */
    public void hideLabels() {
        winGameLabel.setVisible(false);
        loseGameLabel.setVisible(false);
    }

    /**
     * Toggles "Pause" label.
     */
    public void togglePauseLabel() {
        pauseLabel.setVisible(!pauseLabel.isVisible());
    }

    /**
     * Shows help window.
     */
    public void showHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Snake Game help");
        alert.setContentText("WASD - move\nP - pause\nR - restart game\nH - show this message");
        alert.showAndWait().ifPresent(rs -> { });
    }

    /**
     * Draws given GameModel to screen
     *
     * @param model game model
     */
    public void draw(GameModel model) {
        GameField field = model.getField();

        for (int i = 0; i < field.getRowsCount(); ++i) {
            for (int j = 0; j < field.getColsCount(); ++j) {
                Cell cell = field.getCell(j, i);
                Rectangle cellRectangle = getCellRectangle(j, i);

                switch (cell) {
                    case EMPTY:
                        cellRectangle.setFill(Colors.BACKGROUND);
                        break;
                    case FOOD:
                        cellRectangle.setFill(Colors.FOOD);
                        break;
                    case SNAKE_HEAD:
                        cellRectangle.setFill(Colors.SNAKE_HEAD);
                        break;
                    case SNAKE_TAIL:
                        cellRectangle.setFill(Colors.SNAKE_TAIL);
                        break;
                }
            }
        }

        Platform.runLater(() -> scoreLabel.setText("Length: " + model.getScore()));
    }

    private Rectangle getCellRectangle(int x, int y) {
        return cellRectangles.get(y).get(x);
    }

    private static class Colors {
        public static final Color BACKGROUND = Color.BURLYWOOD;
        public static final Color SNAKE_HEAD = Color.ORANGERED;
        public static final Color SNAKE_TAIL = Color.GREEN;
        public static final Color FOOD = Color.RED;
    }
}
