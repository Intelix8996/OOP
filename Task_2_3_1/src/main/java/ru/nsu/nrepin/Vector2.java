package ru.nsu.nrepin;

/**
 * Class that represents integer two-dimensional vector.
 */
public class Vector2 {

    private int componentX = 0;
    private int componentY = 0;

    /**
     * Creates new vector with given x and y components.
     *
     * @param x x component
     * @param y y component
     */
    public Vector2(int x, int y) {
        this.componentX = x;
        this.componentY = y;
    }

    /**
     * Creates new vector similar to given one.
     *
     * @param vector given vector
     */
    public Vector2(Vector2 vector) {
        this.componentX = vector.componentX;
        this.componentY = vector.componentY;
    }

    /**
     * Return x component of the vector.
     *
     * @return x component
     */
    public int getX() {
        return componentX;
    }

    /**
     * Returns y component of the vector.
     *
     * @return y component
     */
    public int getY() {
        return componentY;
    }

    /**
     * Set x component of the vector.
     *
     * @param x new x component
     */
    public void setX(int x) {
        this.componentX = x;
    }

    /**
     * Sets y component of the vector.
     *
     * @param y new y component
     */
    public void setY(int y) {
        this.componentY = y;
    }

    /**
     * Sets x and y components of the vector.
     *
     * @param x new x component
     * @param y new y component
     */
    public void set(int x, int y) {
        this.componentX = x;
        this.componentY = y;
    }

    /**
     * Sets x and y components similar to given vector.
     *
     * @param vec given vector
     */
    public void set(Vector2 vec) {
        this.componentX = vec.componentX;
        this.componentY = vec.componentY;
    }

    /**
     * Adds other vector to current one.
     *
     * @param other vector to add
     */
    public void add(Vector2 other) {
        componentX += other.componentX;
        componentY += other.componentY;
    }

    /**
     * Returns a new vector which is a sum of two given vectors.
     *
     * @param a first vecor
     * @param b second vector
     * @return sum of two vectors
     */
    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.componentX + b.componentX, a.componentY + b.componentY);
    }

    /**
     * Performs component-wise modulo operation on vector.
     *
     * @param other modulo vector
     */
    public void mod(Vector2 other) {
        add(other);

        componentX %= other.componentX;
        componentY %= other.componentY;
    }

    /**
     * Performs component-wise modulo operation on vector by components.
     *
     * @param x x modulo
     * @param y y modulo
     */
    public void mod(int x, int y) {
        add(new Vector2(x, y));

        this.componentX %= x;
        this.componentY %= y;
    }

    @Override
    public boolean equals(Object obj) {
        Vector2 other = (Vector2) obj;

        return componentX == other.componentX && componentY == other.componentY;
    }
}
