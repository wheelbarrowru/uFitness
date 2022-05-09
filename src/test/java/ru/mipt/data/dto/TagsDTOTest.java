package ru.mipt.data.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TagsDTOTest {
    TagsDTO tags1 = new TagsDTO(0, "");
    TagsDTO tags2 = new TagsDTO(0, "");

    @Test
    void compareTo() {
        TagsDTO tagsDTO = new TagsDTO(0, "");
        tagsDTO.setId(0);
        tagsDTO.setMessage("test");
        TagsDTO tagsDTO1 = new TagsDTO(1, "test1");
        TagsDTO tagsDTO3 = new TagsDTO(3, "test");
        Assertions.assertEquals(tagsDTO.compareTo(tagsDTO1), -1);
        Assertions.assertEquals(tagsDTO1.compareTo(tagsDTO), 1);
        Assertions.assertEquals(tagsDTO3.compareTo(tagsDTO), 0);
    }


    @Test
    @SuppressWarnings("all")
    void testEquals() {
        Assertions.assertTrue(tags1.equals(tags2));
    }

    @Test
    void canEqual() {
        Assertions.assertTrue(tags1.canEqual(tags2));
    }

    @Test
    void testHashCode() {
        tags1.setId(0);
        tags2.setId(0);
        Assertions.assertEquals(tags2.hashCode(), tags1.hashCode());
    }

    @Test
    void testToString() {
        Assertions.assertNotNull(tags1.toString());
    }
}