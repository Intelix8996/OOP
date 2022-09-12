package ru.nsu.nrepin;

/**
 * Class that represents binary heap.
 */
public class BinaryHeap {

    /**
     * The size of the heap.
     */
    private int heapSize;
    /**
     * Buffer for the heap.
     */
    private int[] array;

    /**
     * Returns the size of the heap.
     *
     * @return heap size
     */
    public int getHeapSize() {
        return heapSize;
    }

    /**
     * Sets size of the heap.
     *
     * @param heapSize new heap size
     */
    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }

    /**
     * Builds binary heap on given array.
     *
     * @param array array to build heap on
     */
    public BinaryHeap(int[] array) {

        this.array = array;

        this.heapSize = array.length;

        for (int i = this.heapSize / 2 - 1; i >= 0; --i) {
            siftDown(i);
        }
    }

    /**
     * Swaps two given elements i and j of the buffer.
     *
     * @param i first element
     * @param j second element
     */
    public void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    /**
     * Perform sift down on the heap from a given index i.
     *
     * @param i start element
     */
    public void siftDown(int i) {

        int maxVal;
        int maxChild;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left > heapSize - 1) {
            return;
        }

        if (right > heapSize - 1) {
            maxVal = array[left];
            maxChild = left;
        } else {
            if (array[left] >= array[right]) {
                maxVal = array[left];
                maxChild = left;
            } else {
                maxVal = array[right];
                maxChild = right;
            }
        }

        if (array[i] >= maxVal) {
            return;
        }

        swap(i, maxChild);
        siftDown(maxChild);
    }
}
