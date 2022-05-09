package ru.mipt.data.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TagsTest {

    Tags tags1 = new Tags("test1");
    Tags tags2 = new Tags("test1");


    @Test
    void testToString() {
        Assertions.assertEquals(tags1.toString(), tags2.toString());
    }

    @Test
    void getWorkouts() {
        Assertions.assertNull(tags1.getWorkouts());
    }
}