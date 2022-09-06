package ru.nsu.nrepin;

public class HeapSort {

    public static void sort(int[] array){
        BinaryHeap heap = new BinaryHeap(array);

        while (heap.getHeapSize() > 1){
            heap.swap(0, heap.getHeapSize() - 1);
            heap.setHeapSize(heap.getHeapSize() - 1);
            heap.siftDown(0);
        }
    }
}
