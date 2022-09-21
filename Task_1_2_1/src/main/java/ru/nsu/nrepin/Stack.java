package ru.nsu.nrepin;

import java.util.EmptyStackException;

/**
 * Class that implements stack.
 *
 * @param <T> type of elements
 */
public class Stack<T> {

    private static final int INITIAL_CAPACITY = 5;

    private static final int MIN_CAPACITY = 10;

    private T[] buffer;

    private int capacity;

    private int elementCount;

    /**
     * Creates new Stack object.
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        this.elementCount = 0;
        this.capacity = INITIAL_CAPACITY;

        this.buffer = (T[]) new Object[this.capacity];
    }

    /**
     * Pushes an item to stack.
     *
     * @param newItem item that will be pushed
     */
    @SuppressWarnings("unchecked")
    public void push(T newItem) {

        if (newItem == null) {
            throw new IllegalStateException();
        }

        if (this.elementCount >= this.capacity) {
            this.capacity *= 2;

            T[] newBuffer = (T[]) new Object[this.capacity];

            System.arraycopy(this.buffer, 0, newBuffer, 0, this.elementCount);

            this.buffer = newBuffer;
        }

        this.buffer[this.elementCount++] = newItem;
    }

    /**
     * Pops an item from stack.
     *
     * @return popped item
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (elementCount == 0) {
            throw new EmptyStackException();
        }

        if (elementCount < capacity / 2 && this.capacity / 1.5 > MIN_CAPACITY) {
            this.capacity /= 1.5;

            T[] newBuffer = (T[]) new Object[this.capacity];

            System.arraycopy(this.buffer, 0, newBuffer, 0, this.elementCount);

            this.buffer = newBuffer;
        }

        return this.buffer[--this.elementCount];
    }

    /**
     * Pushes all items from given stack.
     *
     * @param newItems source stack
     */
    public void pushStack(Stack<T> newItems) {

        if (newItems == null) {
            throw new IllegalStateException();
        }

        for (int i = 0; i < newItems.elementCount; ++i) {
            push(newItems.buffer[i]);
        }
    }

    /**
     * Pops n items and returns them as new Stack object.
     *
     * @param count n items that will be popped
     * @return stack object with last n elements of initial stack
     */
    public Stack<T> popStack(int count) {

        if (count > this.elementCount) {
            throw new EmptyStackException();
        }

        this.elementCount -= count;

        Stack<T> newStack = new Stack<>();

        for (int i = 0; i < count; ++i) {
            newStack.push(this.buffer[(this.elementCount) + i]);
        }

        return newStack;
    }

    /**
     * Returns the number of elements present in stack.
     *
     * @return stack size
     */
    public int count() {
        return this.elementCount;
    }
}
