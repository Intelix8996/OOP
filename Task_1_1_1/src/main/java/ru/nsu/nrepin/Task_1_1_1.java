package ru.nsu.nrepin;

public class Task_1_1_1 {
    public static void main(String[] args) {
        int[] arr = { 5, 2, 3, 1, 6 };

        HeapSort.sort(arr);

        for (int a : arr) {
            System.out.printf("%d ", a);
        }
    }
}
