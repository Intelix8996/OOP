package ru.nsu.nrepin.controller;

/**
 * Class that is responsible for stepping game.
 */
public class GameThread extends Thread {

    private final GameController controller;

    private final int delay;

    private boolean gameStopped;

    /**
     * Creates new game thread that will step game with some delay.
     *
     * @param delay step delay
     * @param controller assigned controller
     */
    public GameThread(int delay, GameController controller) {
        this.delay = delay;
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                return;
            }

            if (!gameStopped) {
                controller.step();
            }
        }
    }

    /**
     * Stops game.
     */
    public void stopGame() {
        gameStopped = true;
    }

    /**
     * Resumes game.
     */
    public void resumeGame() {
        gameStopped = false;
    }

    /**
     * Returns {@code true} if game is stopped, {@code false} otherwise.
     *
     * @return {@code true} if game is stopped, {@code false} otherwise
     */
    public boolean isGameStopped() {
        return gameStopped;
    }
}
