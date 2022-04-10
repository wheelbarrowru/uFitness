package com.example.data.repository;

import com.example.data.model.User;
import com.example.data.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    Workout findWorkoutById(int id);

}

