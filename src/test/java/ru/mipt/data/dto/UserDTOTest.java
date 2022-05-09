package ru.mipt.data.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mipt.data.Role;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDTOTest {
    UserDTO user = new UserDTO();
    UserDTO user2 = new UserDTO();

    @Test
    void testEquals() {
        UserDTO userDTO1 = new UserDTO(0, "username", "test",
                "test", "test@mail.ru", "test");
        UserDTO userDTO2 = new UserDTO(0, "username", "test",
                "test", "test@mail.ru", "test");
        userDTO2.setId(3);
        Assertions.assertFalse(userDTO1.equals(userDTO2));
        Assertions.assertTrue(userDTO1.equals(userDTO1));
        Assertions.assertFalse(userDTO1.equals(null));
        userDTO2.setId(0);
        Assertions.assertTrue(userDTO1.equals(userDTO2));
    }

    @Test
    void testHashCode() {
        user2.setId(0);
        user.setId(0);
        Assertions.assertEquals(user2.hashCode(), user.hashCode());
    }

    @Test
    void testToString() {
        Assertions.assertNotNull(user.toString());
    }

    @Test
    void getId() {
        user.setId(0);
        Assertions.assertEquals(0, user.getId());
    }

    @Test
    void getUsername() {
        user.setUsername("test");
        Assertions.assertEquals("test", user.getUsername());
    }

    @Test
    void getFirstName() {
        user.setFirstName("test");
        Assertions.assertEquals("test", user.getFirstName());
    }

    @Test
    void getLastName() {
        user.setLastName("test");
        Assertions.assertEquals("test", user.getLastName());
    }

    @Test
    void getEmail() {
        user.setEmail("test");
        Assertions.assertEquals("test", user.getEmail());
    }

    @Test
    void getRoles() {
        Set<Role> set = new HashSet<>();
        set.add(Role.USER);
        user.setRoles(set);
        assertEquals(set, user.getRoles());
    }

    @Test
    void isAllowsMarketing() {
        user.setAllowsMarketing(true);
        assertTrue(user.isAllowsMarketing());
    }

    @Test
    void getPassword() {
        user.setPassword("test");
        Assertions.assertEquals("test", user.getPassword());
    }

    @Test
    void setId() {
        user.setId(0);
        Assertions.assertEquals(0, user.getId());
    }

    @Test
    void setUsername() {
        user.setUsername("test");
        Assertions.assertEquals("test", user.getUsername());
    }

    @Test
    void setFirstName() {
        user.setFirstName("test");
        Assertions.assertEquals("test", user.getFirstName());
    }

    @Test
    void setLastName() {
        user.setLastName("test");
        Assertions.assertEquals("test", user.getLastName());
    }

    @Test
    void setEmail() {
        user.setEmail("test");
        Assertions.assertEquals("test", user.getEmail());
    }

    @Test
    void setRoles() {
        Set<Role> set = new HashSet<>();
        set.add(Role.USER);
        user.setRoles(set);
        assertEquals(set, user.getRoles());
    }

    @Test
    void setAllowsMarketing() {
        user.setAllowsMarketing(true);
        assertTrue(user.isAllowsMarketing());
    }

    @Test
    void setPassword() {
        user.setPassword("test");
        Assertions.assertEquals("test", user.getPassword());
    }
    @Test
    void canEqual() {
        Assertions.assertEquals(true, user.canEqual(user2));
    }
}