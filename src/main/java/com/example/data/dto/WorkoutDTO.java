package com.example.data.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
@ApiModel(value = "Training entity", description = "Complete data of an entity training")
@NoArgsConstructor
public class WorkoutDTO {
    @NotBlank
    private int id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private double rating;

    @NotBlank
    Set<TagsDTO> workoutTags;
}
