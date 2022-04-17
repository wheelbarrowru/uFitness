package com.example.data.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "TagsDTO", description = "Complete data of an entity tags")
@AllArgsConstructor
public class TagsDTO {
    @NotBlank
    @Schema(description = "Tag id")
    private int id;

    @NotBlank
    @Schema(description = "Tag name")
    private String message;
}
