package ru.mipt.data.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AbstractEntityTest {

    AbstractEntity user = new AbstractEntity() {
        @Override
        public int getId() {
            return super.getId();
        }

        @Override
        public void setId(int id) {
            super.setId(id);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    };
    AbstractEntity user2 = new User();

    @Test
    void getId() {
        user.setId(0);
        Assertions.assertEquals(0, user.getId());
    }

    @Test
    void setId() {
        user.setId(0);
        Assertions.assertEquals(0, user.getId());
    }

    @Test
    void testHashCode() {
        user2.setId(0);
        user.setId(0);
        Assertions.assertNotEquals(user.hashCode(), user2.hashCode());
        user.setId(1);
        user2.setId(1);
        Assertions.assertNotEquals(user.hashCode(), user2.hashCode());
    }

    @Test
    @SuppressWarnings("all")
    void testEquals() {
        user2.setId(4);
        user.setId(4);
        Assertions.assertFalse(user.equals(user2));
        Assertions.assertTrue(user.equals(user));
        Assertions.assertFalse(user.equals(null));
        user2.setId(0);
        user.setId(0);
        Assertions.assertTrue(user.equals(user2));
    }
}