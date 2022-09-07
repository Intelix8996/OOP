package ru.nsu.nrepin;

/**
 * Heap sort wrapper class
 */
public class HeapSort {

    /**
     * Sort an array with heap sort
     * @param array array that will be sorted
     */
    public static void sort(int[] array){
        BinaryHeap heap = new BinaryHeap(array);

        while (heap.getHeapSize() > 1){
            heap.swap(0, heap.getHeapSize() - 1);
            heap.setHeapSize(heap.getHeapSize() - 1);
            heap.siftDown(0);
        }
    }
}
