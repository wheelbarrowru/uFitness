package com.example.data.service;

import com.example.data.dto.TagsDTO;
import com.example.data.dto.WorkoutDTO;
import com.example.data.model.Tags;
import com.example.data.model.Workout;
import com.example.data.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FindWorkoutsService {
    private final WorkoutRepository workoutRepository;

    @Autowired
    public FindWorkoutsService(WorkoutRepository workoutRepository) {
        this.workoutRepository=workoutRepository;
    }
    public Set<WorkoutDTO> findWorkoutsByTagsDTO(List<TagsDTO> tagsDTOSet){
        Set<Tags> tagsSet = WorkoutService.convertToTagsSet(new HashSet<>(tagsDTOSet));
        List<Workout> workouts =  workoutRepository.findAll();
        Set<WorkoutDTO> resultSet = new HashSet<>();
        for (Workout workout:
             workouts) {
            if ( workout.getWorkoutTags().containsAll(tagsSet) ){
                resultSet.add(WorkoutService.convertToWorkoutDTO(workout));
            }
        }
        return resultSet;
    }

}