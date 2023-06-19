package ru.nsu.nrepin.util;

/**
 * Thread that can be requested to stop.
 */
public abstract class StoppableThread extends Thread {
    /**
     * Requests a thread to stop.
     */
    public abstract void requestStop();
}
