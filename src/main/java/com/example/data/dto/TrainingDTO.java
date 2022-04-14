package com.example.data.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "Training entity", description = "Complete data of an entity training")
@AllArgsConstructor
public class TrainingDTO {
    @NotBlank
    private int id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private int rating;
}
