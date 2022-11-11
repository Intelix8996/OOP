package ru.nsu.nrepin;

/**
 * <p>This class implements special type of buffer.
 * It is used to construct Z function of string with little amount of memory.</p>
 *
 * <p>First {@code prefixSize} [0; preifxSize-1] fields work as regular array.
 * Next {@code ringBufferSize} [ringBufferSize; +inf) work as ring buffer.
 * This allows data be written to any index, physically data will be stored in ring buffer.
 * Data is mapped to ring buffer by the following formula:
 * ring_buffer_index = ((index - prefixSize) % ringBufferSize) + prefixSize;</p>
 *
 * <p>Example:
 * let {@code zb} be {@code PrefixRingBuffer} with {@code prefixSize} = {@code ringBuffersize} = 5.
 * let {@code b} be zb's internal buffer.
 * Then:
 * [0; 4] will be regular array.
 * [5; 9] will be ring buffer.
 * zb[2] will point to b[2].
 * zb[5] will point to b[5].
 * zb[10] will point to b[5].
 * zb[12] will point to b[7].</p>
 */
public class PrefixRingBuffer {

    private final int prefixSize;

    private final int ringBufferSize;

    private final int[] buffer;

    /**
     * Creates new {@code PrefixRingBuffer}.
     *
     * @param prefixSize size of regular array
     * @param ringBufferSize size of ring buffer
     */
    public PrefixRingBuffer(int prefixSize, int ringBufferSize) {
        this.prefixSize = prefixSize;
        this.ringBufferSize = ringBufferSize;
        this.buffer = new int[prefixSize + ringBufferSize];
    }

    /**
     * Sets a value by index.
     *
     * @param index index
     * @param value value
     */
    public void set(int index, int value) {
        index = normalizeIndex(index);

        buffer[index] = value;
    }

    /**
     * Gets value by index.
     *
     * @param index index
     * @return value stored on index's place
     */
    public int get(int index) {
        index = normalizeIndex(index);

        return buffer[index];
    }

    private int normalizeIndex(int index) {
        if (index >= prefixSize) {
            index = ((index - prefixSize) % ringBufferSize) + prefixSize;
        }

        return index;
    }
}
