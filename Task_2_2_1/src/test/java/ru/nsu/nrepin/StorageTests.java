package ru.nsu.nrepin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for Storage.
 */
public class StorageTests {

    @Test
    public void testStorage() {
        Storage storage = new Storage(15);

        int id = 1234;

        storage.store(id);

        Assertions.assertEquals(storage.take(), id);
    }

}
