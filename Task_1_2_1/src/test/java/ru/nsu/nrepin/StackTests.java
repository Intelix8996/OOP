package ru.nsu.nrepin;

import java.util.EmptyStackException;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for Stack.
 */
public class StackTests {

    @SuppressWarnings("unchecked")
    private <T> void testTrivialUsage(T[] refArray) {
        Stack<T> stack = new Stack<>();

        for (T t : refArray) {
            stack.push(t);
        }

        T[] newArray = (T[]) new Object[refArray.length];

        for (int i = refArray.length - 1; i >= 0; --i) {
            newArray[i] = stack.pop();
        }

        Assertions.assertArrayEquals(refArray, newArray);
    }

    @Test
    @DisplayName("Test trivial usage with different types")
    public void testTrivialUsageWithDifferentTypes() {
        Integer[] refIntegerArray = { 5, 2, 1, 4, 3 };
        Float[] refFloatArray = { 5.5f, 2.1f, 1.6f, 41.2f, 32.424f };
        String[] refStringArray = { "five", "two", "one", "four", "three" };

        testTrivialUsage(refIntegerArray);
        testTrivialUsage(refFloatArray);
        testTrivialUsage(refStringArray);
    }

    @Test
    @DisplayName("Test pop from empty stack")
    public void testBufferUnderrun() {
        Stack<Integer> stack = new Stack<>();

        Assertions.assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    @DisplayName("Test pop from empty stack")
    public void testPushNull() {
        Stack<Integer> stack = new Stack<>();

        Assertions.assertThrows(IllegalStateException.class, () -> stack.push(null));
        Assertions.assertThrows(IllegalStateException.class, () -> stack.pushStack(null));
    }

    @Test
    @DisplayName("Test stack buffer auto expand on overflow")
    public void testBufferAutoExpand() {

        Stack<Integer> stack = new Stack<>();

        final int arrSize = 150000;

        Integer[] newArray = new Integer[arrSize];
        Integer[] refArray = new Integer[arrSize];

        Random rnd = new Random(12345);

        for (int i = 0; i < arrSize; ++i) {
            refArray[i] = rnd.nextInt();
        }

        for (Integer i : refArray) {
            stack.push(i);
        }

        for (int i = refArray.length - 1; i >= 0; --i) {
            newArray[i] = stack.pop();
        }

        Assertions.assertArrayEquals(refArray, newArray);
    }

    @Test
    @DisplayName("Test PushStack()")
    public void testPushStack() {

        Stack<Integer> stackFirst = new Stack<>();

        stackFirst.push(3);
        stackFirst.push(2);
        stackFirst.push(1);

        Stack<Integer> stackSecond = new Stack<>();

        stackSecond.push(6);
        stackSecond.push(5);
        stackSecond.push(4);

        stackFirst.pushStack(stackSecond);

        int size = 6;

        Integer[] refArray = { 3, 2, 1, 6, 5, 4 };
        Integer[] result = new Integer[size];

        for (int i = size - 1; i >= 0; --i) {
            result[i] = stackFirst.pop();
        }

        Assertions.assertArrayEquals(refArray, result);
    }

    @Test
    @DisplayName("Test PopStack()")
    public void testPopStackTrivial() {

        Stack<Integer> stackFirst = new Stack<>();

        stackFirst.push(3);
        stackFirst.push(2);
        stackFirst.push(1);
        stackFirst.push(6);
        stackFirst.push(5);
        stackFirst.push(4);

        int size = 4;

        Stack<Integer> stackSecond = stackFirst.popStack(size);

        Integer[] result = new Integer[size];

        for (int i = size - 1; i >= 0; --i) {
            result[i] = stackSecond.pop();
        }

        Integer[] refPoppedArray = { 1, 6, 5, 4 };

        Assertions.assertArrayEquals(refPoppedArray, result);

        size = 2;

        result = new Integer[size];

        for (int i = size - 1; i >= 0; --i) {
            result[i] = stackFirst.pop();
        }

        Integer[] refInitialArray = { 3, 2 };

        Assertions.assertArrayEquals(refInitialArray, result);
    }

    @Test
    @DisplayName("Test PopStack() with full size of buffer")
    public void testPopStackFull() {

        Stack<Integer> stackFirst = new Stack<>();

        stackFirst.push(3);
        stackFirst.push(2);
        stackFirst.push(1);
        stackFirst.push(6);
        stackFirst.push(5);
        stackFirst.push(4);

        int size = 6;

        Stack<Integer> stackSecond = stackFirst.popStack(size);

        Integer[] refPoppedArray = { 3, 2, 1, 6, 5, 4 };

        Integer[] result = new Integer[size];

        for (int i = size - 1; i >= 0; --i) {
            result[i] = stackSecond.pop();
        }

        Assertions.assertArrayEquals(refPoppedArray, result);

        Assertions.assertEquals(0, stackFirst.count());
    }

    @Test
    @DisplayName("Test PopStack() with argument more than elements count")
    public void testPopStackWithUnderrun() {

        Stack<Integer> stackFirst = new Stack<>();

        stackFirst.push(3);
        stackFirst.push(2);
        stackFirst.push(1);
        stackFirst.push(6);
        stackFirst.push(5);
        stackFirst.push(4);

        Assertions.assertThrows(EmptyStackException.class, () -> stackFirst.popStack(10));
    }

    @Test
    @DisplayName("Test count()")
    public void testCount() {

        Stack<Integer> stack = new Stack<>();

        final int size = 150000;

        Random rnd = new Random(12345);

        for (int i = 0; i < size; ++i) {
            stack.push(rnd.nextInt());
        }

        Assertions.assertEquals(size, stack.count());
    }
}
