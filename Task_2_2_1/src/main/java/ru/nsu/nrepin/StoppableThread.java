package ru.nsu.nrepin;

/**
 * Thread that can be requested to stop.
 */
public abstract class StoppableThread extends Thread {
    /**
     * Requests a thread to stop.
     */
    abstract void requestStop();
}
