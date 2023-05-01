package ru.nsu.nrepin;

public class Vector2 {

    private int x = 0;
    private int y = 0;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 vec){
        this.x = vec.x;
        this.y = vec.y;
    }

    public void add(Vector2 other) {
        x += other.x;
        y += other.y;
    }

    public void mod(Vector2 other) {
        add(other);

        x %= other.x;
        y %= other.y;
    }

    public void mod(int x, int y) {
        add(new Vector2(x, y));

        this.x %= x;
        this.y %= y;
    }

    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }
}
