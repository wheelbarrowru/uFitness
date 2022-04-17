package com.example.data.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
@ApiModel(value = "WorkoutDTO", description = "Complete data of an entity training")
@NoArgsConstructor
public class WorkoutDTO {
    @NotBlank
    @Schema(description = "Workout id")
    private int id;

    @NotBlank
    @Schema(description = "Workout title")
    private String title;

    @NotBlank
    @Schema(description = "Workout description")
    private String description;

    @NotBlank
    @Schema(description = "Workout rating")
    private double rating;

    @NotBlank
    @Schema(description = "Workout's tags")
    Set<TagsDTO> workoutTags;
}
