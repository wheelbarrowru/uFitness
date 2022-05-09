package ru.mipt.data.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mipt.data.dto.UserDTO;

class WorkoutVotedUserTest {
    WorkoutVotedUser workoutVotedUser = new WorkoutVotedUser();

    @Test
    void testToString() {
        Assertions.assertNotNull(workoutVotedUser.toString());
    }

    @Test
    @SuppressWarnings("all")
    void testEquals() {
        workoutVotedUser.setUserVote(5);
        workoutVotedUser.setUserId(2);
        workoutVotedUser.setWorkoutId(37);
        workoutVotedUser.setId(1);
        WorkoutVotedUser workoutVotedUser2 = new WorkoutVotedUser();
        workoutVotedUser2.setUserVote(workoutVotedUser.getUserVote());
        workoutVotedUser2.setUserId(workoutVotedUser.getUserId());
        workoutVotedUser2.setWorkoutId(workoutVotedUser.getWorkoutId());
        workoutVotedUser2.setId(workoutVotedUser.getId());
        Assertions.assertTrue(workoutVotedUser.equals(workoutVotedUser2));
        Assertions.assertFalse(workoutVotedUser.equals(null));
        Assertions.assertFalse(workoutVotedUser.equals(new UserDTO()));
        workoutVotedUser2.setUserId(1000);
        Assertions.assertFalse(workoutVotedUser.equals(workoutVotedUser2));
        Assertions.assertTrue(workoutVotedUser.equals(workoutVotedUser));
    }

    @Test
    void testHashCode() {
        workoutVotedUser.setUserId(0);
        WorkoutVotedUser workoutVotedUser2 = new WorkoutVotedUser();
        workoutVotedUser2.setUserId(3);
        Assertions.assertNotEquals(workoutVotedUser.hashCode(), workoutVotedUser2.hashCode());
    }
}