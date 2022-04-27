package ru.mipt.data.service;

import ru.mipt.data.dto.TagsDTO;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.model.Tags;
import ru.mipt.data.model.Workout;
import ru.mipt.data.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is service class responsible for finding workouts
 */
@Service
public class FindWorkoutsService {
    private final WorkoutRepository workoutRepository;

    /**
     * This method find workoutService by workoutRepository
     *
     * @param workoutRepository workoutRepository
     * @see WorkoutRepository
     */
    @Autowired
    public FindWorkoutsService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    /**
     * This method make set of workouts with Tags in List tagsDTOSet
     *
     * @param tagsDTOSet Set of TagsDTO
     * @return Set of WorkoutDTO
     * @see WorkoutService#convertToTagsSet(Set)
     * @see WorkoutService#convertToWorkoutDTO(Workout)
     */
    public Set<WorkoutDTO> findWorkoutsByTagsDTO(List<TagsDTO> tagsDTOSet) {
        Set<Tags> tagsSet = WorkoutService.convertToTagsSet(new HashSet<>(tagsDTOSet));
        List<Workout> workouts = workoutRepository.findAll();
        Set<WorkoutDTO> resultSet = new HashSet<>();
        for (Workout workout :
                workouts) {
            if (workout.getWorkoutTags().containsAll(tagsSet)) {
                resultSet.add(WorkoutService.convertToWorkoutDTO(workout));
            }
        }
        return resultSet;
    }

}