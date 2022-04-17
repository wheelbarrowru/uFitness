package com.example.data.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Class WorkoutDTO connects Workout with front classes
 * <p>
 * Uses Bean Validation (JSR-303) annotations for automatic validation.
 */
@Data
@AllArgsConstructor
@ApiModel(value = "WorkoutDTO", description = "Complete data of an entity training")
@NoArgsConstructor
public class WorkoutDTO {
    /**
     * Workout's generated in database id
     */
    @NotBlank
    @Schema(description = "Workout id")
    private int id;

    /**
     * "Name" of Workout
     */
    @NotBlank
    @Schema(description = "Workout title")
    private String title;

    /**
     * Description of exercises in workout
     */
    @NotBlank
    @Schema(description = "Workout description")
    private String description;

    /**
     * User's rating of workout
     */
    @NotBlank
    @Schema(description = "Workout rating")
    private double rating;

    /**
     * Tags of workout
     */
    @NotBlank
    @Schema(description = "Workout's tags")
    Set<TagsDTO> workoutTags;
}
