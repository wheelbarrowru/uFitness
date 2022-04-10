package com.example.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
public class WorkoutDTO {
    @NotBlank
    private int id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private int rating;

    Set<TagsDTO> workoutTags;
}
