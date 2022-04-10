package com.example.data.service;

import com.example.data.dto.TagsDTO;
import com.example.data.dto.WorkoutDTO;
import com.example.data.model.Tags;
import com.example.data.model.Workout;
import com.example.data.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository repository) {
        this.workoutRepository = repository;
    }

    public Optional<Workout> get(int id) { return workoutRepository.findById(id); }
    //FIXME
    public WorkoutDTO getDTO(int id){return convertToWorkoutDTO(get(id).orElse(new Workout())); }

    public Workout update(WorkoutDTO entity) { return workoutRepository.save(convertToWorkout(entity)); }

    public void delete(int id) {
        workoutRepository.deleteById(id);
    }

    public Page<Workout> list(Pageable pageable) {
        return workoutRepository.findAll(pageable);
    }

    public int count() {
        return (int) workoutRepository.count();
    }

    private Workout convertToWorkout(WorkoutDTO workoutDTO){
        Workout workout = new Workout();
        workout.setTitle(workoutDTO.getTitle());
        workout.setDescription(workoutDTO.getDescription());
        workout.setRating(workoutDTO.getRating());
        workout.setWorkoutTags(workoutDTO.getWorkoutTags().stream()
                .map(TagsService::convertToTags)
                .collect(Collectors.toSet()));

        return workout;
    }
    private WorkoutDTO convertToWorkoutDTO(Workout workout){
        WorkoutDTO workoutDTO = new WorkoutDTO(workout.getId(),
                workout.getTitle(),
                workout.getDescription(),
                workout.getRating(),
                workout.getWorkoutTags().stream()
                        .map(TagsService::convertToTagsDTO)
                        .collect(Collectors.toSet()));
        return workoutDTO;
    }

}
