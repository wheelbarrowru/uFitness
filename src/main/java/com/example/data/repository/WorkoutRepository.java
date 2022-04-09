package com.example.data.repository;

import com.example.data.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {
    //читай статью
}
