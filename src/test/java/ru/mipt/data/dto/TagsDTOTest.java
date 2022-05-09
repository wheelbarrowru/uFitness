package ru.mipt.data.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TagsDTOTest {

    @Test
    void compareTo() {
        TagsDTO tagsDTO = new TagsDTO(0, "test");
        TagsDTO tagsDTO1 = new TagsDTO(1, "test1");
        TagsDTO tagsDTO3 = new TagsDTO(3, "test");
        Assertions.assertEquals(tagsDTO.compareTo(tagsDTO1), -1);
        Assertions.assertEquals(tagsDTO1.compareTo(tagsDTO), 1);
        Assertions.assertEquals(tagsDTO3.compareTo(tagsDTO), 0);
    }

}