package com.example.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class TagsDTO {
    @NotBlank
    private int id;

    @NotBlank
    private String message;
}
