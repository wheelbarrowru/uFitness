package com.example.data.service;

import com.example.data.model.Workout;
import com.example.data.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository repository) {
        this.workoutRepository = repository;
    }

    public Optional<Workout> get(int id) { return workoutRepository.findById(id); }

    public Workout update(Workout entity) { return workoutRepository.save(entity); }

    public void delete(int id) {
        workoutRepository.deleteById(id);
    }

    public Page<Workout> list(Pageable pageable) {
        return workoutRepository.findAll(pageable);
    }

    public int count() {
        return (int) workoutRepository.count();
    }
}
