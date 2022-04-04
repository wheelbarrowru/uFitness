package com.example.data.repository;

import com.example.data.entity.SamplePerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkoutRepository extends JpaRepository<SamplePerson, UUID> {
    //читай статью
}
