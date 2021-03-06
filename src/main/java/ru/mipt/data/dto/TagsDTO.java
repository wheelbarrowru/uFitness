package ru.mipt.data.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Class TagsDTO connects Tags with front classes
 * <p>
 * Uses Bean Validation (JSR-303) annotations for automatic validation.
 */
@Data
@ApiModel(value = "TagsDTO", description = "Complete data of an entity tags")
@AllArgsConstructor
public class TagsDTO implements Comparable<TagsDTO> {
    /**
     * TagsDTO's id
     */
    @NotBlank
    @Schema(description = "Tag id")
    private int id;

    /**
     * TagsDTO's message
     */
    @NotBlank
    @Schema(description = "Tag name")
    private String message;

    /**
     * Compare Tags using messages
     *
     * @param o tagsDTO
     * @return compare result
     */
    @Override
    public int compareTo(TagsDTO o) {
        return this.getMessage().compareTo(o.getMessage());
    }
}
