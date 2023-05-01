package ru.nsu.nrepin;

public class GameThread extends Thread {

    private final GameController controller;

    private final int delay;

    private boolean gameStopped;

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

    public void stopGame() {
        gameStopped = true;
    }

    public void resumeGame() {
        gameStopped = false;
    }
}
