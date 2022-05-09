package ru.mipt.data.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class WorkoutDTOTest {
    WorkoutDTO workout = new WorkoutDTO();
    WorkoutDTO workout2 = new WorkoutDTO();

    @Test
    void compareTo() {
        WorkoutDTO workoutDTO = new WorkoutDTO(0, "test", "test", 5, 0, new HashSet<>());
        workoutDTO.setTitle("test");
        workoutDTO.setDescription("test");
        workoutDTO.setId(0);
        workoutDTO.setRating(5);
        workoutDTO.setWorkoutTags(new HashSet<>());
        workoutDTO.setAuthorId(0);
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


    @Test
    void getId() {
        workout.setId(0);
        Assertions.assertEquals(0, workout.getId());
    }

    @Test
    void getTitle() {
        workout.setTitle("test");
        Assertions.assertEquals("test", workout.getTitle());
    }

    @Test
    void getDescription() {
        workout.setDescription("test");
        Assertions.assertEquals("test", workout.getDescription());
    }

    @Test
    void getRating() {
        workout.setRating(5);
        Assertions.assertEquals(5, workout.getRating());
    }

    @Test
    void getAuthorId() {
        workout.setAuthorId(0);
        Assertions.assertEquals(0, workout.getAuthorId());
    }

    @Test
    void getWorkoutTags() {
        HashSet<TagsDTO> hashSet = new HashSet<>();
        workout.setWorkoutTags(hashSet);
        Assertions.assertEquals(hashSet, workout.getWorkoutTags());

    }

    @Test
    void setId() {
        workout.setId(0);
        Assertions.assertEquals(0, workout.getId());
    }

    @Test
    void setTitle() {
        workout.setTitle("test");
        Assertions.assertEquals("test", workout.getTitle());
    }

    @Test
    void setDescription() {
        workout.setDescription("test");
        Assertions.assertEquals("test", workout.getDescription());
    }

    @Test
    void setRating() {
        workout.setRating(5);
        Assertions.assertEquals(5, workout.getRating());
    }

    @Test
    void setAuthorId() {
        workout.setAuthorId(0);
        Assertions.assertEquals(0, workout.getAuthorId());
    }

    @Test
    void setWorkoutTags() {
        HashSet<TagsDTO> hashSet = new HashSet<>();
        workout.setWorkoutTags(hashSet);
        Assertions.assertEquals(hashSet, workout.getWorkoutTags());
    }

    @Test
    @SuppressWarnings("all")
    void testEquals() {
        WorkoutDTO workoutDTO = new WorkoutDTO(0, "test", "test", 5, 0, new HashSet<>());
        WorkoutDTO workoutDTO1 = new WorkoutDTO(0, "test", "test", 5, 0, new HashSet<>());
        workoutDTO1.setId(3);
        Assertions.assertFalse(workoutDTO.equals(workoutDTO1));
        Assertions.assertTrue(workoutDTO.equals(workoutDTO));
        Assertions.assertFalse(workoutDTO.equals(null));
        workoutDTO1.setId(0);
        Assertions.assertTrue(workoutDTO.equals(workoutDTO1));
    }

    @Test
    void canEqual() {
        Assertions.assertTrue(workout.canEqual(workout2));
    }

    @Test
    void testHashCode() {
        workout2.setId(0);
        workout.setId(0);
        Assertions.assertEquals(workout2.hashCode(), workout.hashCode());
    }

    @Test
    void testToString() {
        Assertions.assertNotNull(workout.toString());

    }
}