package ru.nsu.nrepin;

import java.util.EmptyStackException;

/**
 * Class that implements stack
 *
 * @param <T> type of elements
 */
@SuppressWarnings("unchecked")
public class Stack<T> {

    private T[] buffer;

    private int capacity;

    private int elementCount;

    /**
     * Creates new Stack object
     */
    public Stack() {
        this.elementCount = 0;
        this.capacity = 5;

        this.buffer = (T[]) new Object[this.capacity];
    }

    /**
     * Pushes an item to stack
     *
     * @param newItem item that will be pushed
     */
    public void push(T newItem) {
        if (this.elementCount >= this.capacity) {
            this.capacity *= 2;

            T[] newBuffer = (T[]) new Object[this.capacity];

            System.arraycopy(this.buffer, 0, newBuffer, 0, this.elementCount);

            this.buffer = newBuffer;
        }

        this.buffer[this.elementCount++] = newItem;
    }

    /**
     * Pops an item from stack
     *
     * @return popped item
     */
    public T pop() {
        if (elementCount == 0){
            throw new EmptyStackException();
        }

        return this.buffer[--this.elementCount];
    }

    /**
     * Pushes all items from given stack
     *
     * @param newItems source stack
     */
    public void pushStack(Stack<T> newItems) {
        for (int i = 0; i < newItems.elementCount; ++i) {
            push(newItems.buffer[i]);
        }
    }

    /**
     * Pops n items and returns them as new Stack object
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
     * Returns the number of elements present in stack
     *
     * @return stack size
     */
    public int count() {
        return this.elementCount;
    }
}
