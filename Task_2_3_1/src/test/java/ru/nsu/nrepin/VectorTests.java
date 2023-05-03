package ru.nsu.nrepin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for Vector2 class.
 */
public class VectorTests {

    /**
     * Tests operations with vectors.
     */
    @Test
    public void testVector() {
        Vector2 vec = new Vector2(2, 3);

        Assertions.assertEquals(2, vec.getX());
        Assertions.assertEquals(3, vec.getY());

        Vector2 vec2 = new Vector2(vec);

        Assertions.assertEquals(2, vec2.getX());
        Assertions.assertEquals(3, vec2.getY());

        vec2.setX(4);
        vec2.setY(5);

        Assertions.assertEquals(4, vec2.getX());
        Assertions.assertEquals(5, vec2.getY());

        vec.set(7, 8);

        Assertions.assertEquals(7, vec.getX());
        Assertions.assertEquals(8, vec.getY());

        vec2.set(vec);

        Assertions.assertEquals(7, vec2.getX());
        Assertions.assertEquals(8, vec2.getY());

        Vector2 a = new Vector2(2, 3);
        Vector2 b = new Vector2(6, 7);

        vec = Vector2.add(a, b);

        Assertions.assertEquals(8, vec.getX());
        Assertions.assertEquals(10, vec.getY());

        vec2 = new Vector2(vec);

        vec.mod(3, 4);

        Assertions.assertEquals(2, vec.getX());
        Assertions.assertEquals(2, vec.getY());

        vec2.mod(new Vector2(5, 6));

        Assertions.assertEquals(3, vec2.getX());
        Assertions.assertEquals(4, vec2.getY());
    }
}
