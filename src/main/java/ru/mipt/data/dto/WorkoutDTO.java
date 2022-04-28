package ru.mipt.data.dto;

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
public class WorkoutDTO implements Comparable<WorkoutDTO> {
    /**
     * Workout's generated in database id
     */
    @NotBlank
    @Schema(description = "Workout id")
    private int id;

    /**
     * Compare workoutDTO using rating as first and title as second
     *
     * @param o to compare
     * @return compare result
     */
    @Override
    public int compareTo(WorkoutDTO o) {
        int rating = (int) (o.getRating() - this.getRating());
        return rating != 0 ? rating : this.getTitle().compareTo(o.getTitle());
    }

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
