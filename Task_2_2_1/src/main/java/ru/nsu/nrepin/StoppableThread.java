package ru.nsu.nrepin;

public abstract class StoppableThread extends Thread {
    /**
     * Requests a thread to stop.
     */
    abstract void requestStop();
}
