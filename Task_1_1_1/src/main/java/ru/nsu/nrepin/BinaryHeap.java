package ru.nsu.nrepin;

public class BinaryHeap {

    private int heapSize;
    private int[] array;

    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }

    public BinaryHeap(int[] array) {

        this.array = array;

        this.heapSize = array.length;

        for (int i = this.heapSize / 2 - 1; i >= 0; --i){
            siftDown(i);
        }
    }

    public void swap(int i, int j){
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
    public void siftDown(int i){

        int maxVal;
        int maxChild;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left > heapSize - 1)
            return;

        if (right > heapSize - 1) {
            maxVal = array[left];
            maxChild = left;
        }
        else {
            if (array[left] >= array[right]) {
                maxVal = array[left];
                maxChild = left;
            }
            else {
                maxVal = array[right];
                maxChild = right;
            }
        }

        if (array[i] >= maxVal)
            return;

        swap(i, maxChild);
        siftDown(maxChild);
    }
}
