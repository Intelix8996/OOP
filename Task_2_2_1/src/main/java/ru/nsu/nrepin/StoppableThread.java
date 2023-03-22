package ru.nsu.nrepin;

public abstract class StoppableThread extends Thread {
    abstract void requestStop();
}
