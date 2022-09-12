package ru.nsu.nrepin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Random;

public class HeapSortTests {

    @Test
    public void testEmptyArray(){
        int[] array = {  };
        int[] refArray = {  };

        HeapSort.sort(array);

        Assertions.assertArrayEquals(refArray, array);
    }

    @Test
    public void testOneElemArray(){
        int[] array = { 1 };
        int[] refArray = { 1 };

        HeapSort.sort(array);

        Assertions.assertArrayEquals(refArray, array);
    }

    @Test
    public void testTwoElemArray(){
        int[] array = { 5, 2 };
        int[] refArray = { 2, 5 };

        HeapSort.sort(array);

        Assertions.assertArrayEquals(refArray, array);
    }

    @Test
    public void testFiveElemArray(){
        int[] array = { 6, 1, 2, 3, 4 };
        int[] refArray = { 1, 2, 3, 4, 6 };

        HeapSort.sort(array);

        Assertions.assertArrayEquals(refArray, array);
    }

    @Test
    public void testBigArray(){
        final int arrSize = 150000;

        int[] array = new int[arrSize];
        int[] refArray = new int[arrSize];

        Random rnd = new Random();

        for (int i = 0; i < arrSize; ++i){
            int num = rnd.nextInt();
            array[i] = num;
            refArray[i] = num;
        }

        HeapSort.sort(array);
        Arrays.sort(refArray);

        Assertions.assertArrayEquals(refArray, array);
    }
}
