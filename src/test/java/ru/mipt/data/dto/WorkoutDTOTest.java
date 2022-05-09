package ru.mipt.data.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class WorkoutDTOTest {

    @Test
    void compareTo() {
        WorkoutDTO workoutDTO = new WorkoutDTO(0, "test", "test", 5, 0, new HashSet<>());
        WorkoutDTO workoutDTO1 = new WorkoutDTO(1, "test1", "test1", 3, 0, new HashSet<>());
        WorkoutDTO workoutDTO2 = new WorkoutDTO(0, "test", "test", 4, 0, new HashSet<>());
        WorkoutDTO workoutDTO3 = new WorkoutDTO(0, "test1", "test1", 5, 0, new HashSet<>());
        WorkoutDTO workoutDTO4 = new WorkoutDTO(0, "test", "test", 5, 0, new HashSet<>());
        Assertions.assertEquals(workoutDTO.compareTo(workoutDTO1), -200);
        Assertions.assertEquals(workoutDTO1.compareTo(workoutDTO2), 100);
        Assertions.assertEquals(workoutDTO.compareTo(workoutDTO3), -1);
        Assertions.assertEquals(workoutDTO3.compareTo(workoutDTO), 1);
        Assertions.assertEquals(workoutDTO.compareTo(workoutDTO4), 0);
    }
}