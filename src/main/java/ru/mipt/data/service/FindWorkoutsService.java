package ru.mipt.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.data.dto.TagsDTO;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.model.Tags;
import ru.mipt.data.model.Workout;
import ru.mipt.data.repository.WorkoutRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

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
     * @param title      searching title
     * @param tagsDTOSet Set of TagsDTO
     * @return Set of WorkoutDTO
     * @see WorkoutService#convertToTagsSet(Set)
     * @see WorkoutService#convertToWorkoutDTO(Workout)
     */
    public Set<WorkoutDTO> findWorkoutsByTitleAndTagsDTO(String title, List<TagsDTO> tagsDTOSet) {

        Set<Tags> tagsSet = WorkoutService.convertToTagsSet(new HashSet<>(tagsDTOSet));
        List<Workout> workouts = workoutRepository.findAll();
        Set<WorkoutDTO> resultSet = new HashSet<>();
        Pattern pattern = Pattern.compile(String.format(".*%s.*", title));
        for (Workout workout :
                workouts) {
            if (workout.getWorkoutTags().containsAll(tagsSet) && pattern.matcher(workout.getTitle()).matches()) {
                resultSet.add(WorkoutService.convertToWorkoutDTO(workout));
            }
        }
        return resultSet;
    }

    /**
     * Search workouts by author id
     *
     * @param authorId for search
     * @return set of workoutDTO
     */
    //TODO test me
    public Set<WorkoutDTO> findWorkoutByAuthorId(int authorId) {
        return WorkoutService.convertToWorkoutDTOSet(workoutRepository.findByAuthorId(authorId));
    }

}
