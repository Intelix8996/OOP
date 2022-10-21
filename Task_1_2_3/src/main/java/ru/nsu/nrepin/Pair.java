package ru.nsu.nrepin;

/**
 * This class represents an ordered pair.
 *
 * @param <A> left element type
 * @param <B> right element type
 */
public class Pair<A, B> {
    private A left;
    private B right;

    /**
     * Creates new pair with given value.
     *
     * @param left left element value
     * @param right right element value
     */
    public Pair(A left, B right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Returns left element of a pair.
     *
     * @return left element
     */
    public A getLeft() {
        return left;
    }

    /**
     * Sets left element of a pair.
     *
     * @param left new left element
     */
    public void setLeft(A left) {
        this.left = left;
    }

    /**
     * Returns right element of a pair.
     *
     * @return right element
     */
    public B getRight() {
        return right;
    }

    /**
     * Sets right element of a pair.
     *
     * @param right new left element
     */
    public void setRight(B right) {
        this.right = right;
    }
}
