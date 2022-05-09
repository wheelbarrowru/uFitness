package ru.mipt.data.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {
    User user = new User();
    User user2 = new User();

    @Test
    void testHashCode() {
        user2.setId(4);
        user.setId(4);
        Assertions.assertEquals(user2.hashCode(), user.hashCode());
    }

    @Test
    void getRoles() {
        Assertions.assertNull(user.getRoles());
    }

    @Test
    void testToString() {
        Assertions.assertNotNull(user.toString());
    }
}