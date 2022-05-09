package ru.mipt.data.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class WorkoutTest {
    Workout workout = new Workout();

    @Test
    void getUserFavoriteWorkouts() {
        Assertions.assertNull(workout.getUserFavoriteWorkouts());
    }

    @Test
    void setUserFavoriteWorkouts() {
        Set<User> favorites = new HashSet<>();
        favorites.add(new User());
        workout.setUserFavoriteWorkouts(favorites);
        Assertions.assertEquals(favorites, workout.getUserFavoriteWorkouts());
    }
}