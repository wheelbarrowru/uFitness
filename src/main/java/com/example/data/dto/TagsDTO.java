package com.example.data.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "Tags entity", description = "Complete data of an entity tags")
@AllArgsConstructor
public class TagsDTO {
    @NotBlank
    private int id;

    @NotBlank
    private String message;
}
