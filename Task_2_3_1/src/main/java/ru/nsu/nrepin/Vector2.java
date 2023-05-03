package ru.nsu.nrepin;

/**
 * Class that represents integer two-dimensional vector.
 */
public class Vector2 {

    private int xComponent = 0;
    private int yComponent = 0;

    /**
     * Creates new vector with given x and y components.
     *
     * @param x x component
     * @param y y component
     */
    public Vector2(int x, int y) {
        this.xComponent = x;
        this.yComponent = y;
    }

    /**
     * Creates new vector similar to given one.
     *
     * @param vector given vector
     */
    public Vector2(Vector2 vector) {
        this.xComponent = vector.xComponent;
        this.yComponent = vector.yComponent;
    }

    /**
     * Return x component of the vector.
     *
     * @return x component
     */
    public int getX() {
        return xComponent;
    }

    /**
     * Returns y component of the vector.
     *
     * @return y component
     */
    public int getY() {
        return yComponent;
    }

    /**
     * Set x component of the vector.
     *
     * @param x new x component
     */
    public void setX(int x) {
        this.xComponent = x;
    }

    /**
     * Sets y component of the vector.
     *
     * @param y new y component
     */
    public void setY(int y) {
        this.yComponent = y;
    }

    /**
     * Sets x and y components of the vector.
     *
     * @param x new x component
     * @param y new y component
     */
    public void set(int x, int y) {
        this.xComponent = x;
        this.yComponent = y;
    }

    /**
     * Sets x and y components similar to given vector.
     *
     * @param vec given vector
     */
    public void set(Vector2 vec) {
        this.xComponent = vec.xComponent;
        this.yComponent = vec.yComponent;
    }

    /**
     * Adds other vector to current one.
     *
     * @param other vector to add
     */
    public void add(Vector2 other) {
        xComponent += other.xComponent;
        yComponent += other.yComponent;
    }

    /**
     * Returns a new vector which is a sum of two given vectors.
     *
     * @param a first vecor
     * @param b second vector
     * @return sum of two vectors
     */
    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.xComponent + b.xComponent, a.yComponent + b.yComponent);
    }

    /**
     * Performs component-wise modulo operation on vector.
     *
     * @param other modulo vector
     */
    public void mod(Vector2 other) {
        add(other);

        xComponent %= other.xComponent;
        yComponent %= other.yComponent;
    }

    /**
     * Performs component-wise modulo operation on vector by components.
     *
     * @param x x modulo
     * @param y y modulo
     */
    public void mod(int x, int y) {
        add(new Vector2(x, y));

        this.xComponent %= x;
        this.yComponent %= y;
    }
}
