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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

/**
 * WorkoutService Class  is responsible for interacting with WorkoutDTO and Workout
 */
@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    /**
     * Constructor of WorkoutService
     * @param repository WorkoutRepository
     */
    @Autowired
    public WorkoutService(WorkoutRepository repository) {
        this.workoutRepository = repository;
    }

    /**
     * This method returns workout which was found by id
     * @param id Workout id
     * @return Workout
     * @see WorkoutRepository#findById(Object)
     */
    public Optional<Workout> get(int id) {
        return workoutRepository.findById(id);
    }

    /**
     * This method get Workout from id of Workout
     * @param id workout id
     * @return WorkoutDTO
     */
    //FIXME
    public WorkoutDTO getDTO(int id) {
        return convertToWorkoutDTO(get(id).orElse(new Workout()));
    }

    /**
     * Method for updating userRepository with Workout
     * @param entity WorkoutDTO
     * @return workoutRepository with updated WorkoutDTO
     * @see WorkoutRepository#save(Object)
     */
    public Workout update(WorkoutDTO entity) {
        return workoutRepository.save(convertToWorkout(entity));
    }

    /**
     * Method for deleting workout
     * @param id workout id
     * @see WorkoutRepository#deleteById(Object)
     */
    public void delete(int id) {
        workoutRepository.deleteById(id);
    }

    /**
     * This Method return List of pageable workouts
     * @param pageable Workout
     * @return WorkoutRepository
     * @see WorkoutRepository#findAll()
     */
    public Page<Workout> list(Pageable pageable) {
        return workoutRepository.findAll(pageable);
    }

    public int count() {
        return (int) workoutRepository.count();
    }

    /**
     * Method for counting new rating of workout after user's reaction
     * @param workoutDTO WorkoutDTO object
     * @param valueString user's rating of the workout
     * @see WorkoutRepository#findById(Object)
     * @see WorkoutRepository#updateRatingAndCount(int, double, int)
     * @see WorkoutDTO#getId()
     * @see Workout#getCountVote()
     * @exception NoSuchElementException throws if you can't update rating
     */
    @Transactional
    public void updateRating(WorkoutDTO workoutDTO, String valueString) {
        int value = Integer.parseInt(valueString);
        try {
            Workout workout = workoutRepository.findById(workoutDTO.getId()).orElseThrow();
            int count = workout.getCountVote();
            workoutRepository.updateRatingAndCount(workoutDTO.getId(), (double) Math.round(100 * (workout.getRating() * count + value) / (count + 1)) / 100, count + 1);
        } catch (NoSuchElementException e) {
            System.out.println("Ошибка updateRating: " + workoutDTO);
        }
    }

    /**
     * Method for converting WorkoutDTO to workout
     * @param workoutDTO WorkoutDTO
     * @return Workout
     * @see Workout#setTitle(String)
     * @see Workout#setDescription(String)
     * @see Workout#setRating(double)
     * @see Workout#setWorkoutTags(Set)
     */
    private Workout convertToWorkout(WorkoutDTO workoutDTO) {
        Workout workout = new Workout();
        workout.setTitle(workoutDTO.getTitle());
        workout.setDescription(workoutDTO.getDescription());
        workout.setRating(workoutDTO.getRating());
        workout.setWorkoutTags(convertToTagsSet(workoutDTO.getWorkoutTags()));

        return workout;
    }

    /**
     * Method for converting Workout to WorkoutDTO
     * @param workout Workout entity
     * @return WorkoutDTO
     * @see WorkoutDTO#getId()
     * @see WorkoutDTO#getDescription() ()
     * @see WorkoutDTO#getRating() ()
     * @see WorkoutDTO#getTitle() ()
     * @see WorkoutService#convertToTagsDTOSet(Set)
     */
    protected static WorkoutDTO convertToWorkoutDTO(Workout workout) {
        return new WorkoutDTO(workout.getId(),
                workout.getTitle(),
                workout.getDescription(),
                workout.getRating(),
                convertToTagsDTOSet(workout.getWorkoutTags()));
    }

    /**
     * This method convert TagsDTO into Set of Tags
     * @param tagsDTOSet Set of TagsDTO
     * @return Tags
     * @see TagsService#convertToTags(TagsDTO)
     */
    protected static Set<Tags> convertToTagsSet(Set<TagsDTO> tagsDTOSet) {
        Set<Tags> tags = new HashSet<>();
        for (TagsDTO tagsDTO : tagsDTOSet) {
            tags.add(TagsService.convertToTags(tagsDTO));
        }
        return tags;
    }

    /**
     * This method convert Tags into TagsDTO
     * @param tagsSet Set<Tags>
     * @return TagsDTO
     * @see TagsService#convertToTagsDTO(Tags)
     */
    private static Set<TagsDTO> convertToTagsDTOSet(Set<Tags> tagsSet) {
        Set<TagsDTO> tagsDTO = new HashSet<>();
        for (Tags tags : tagsSet) {
            tagsDTO.add(TagsService.convertToTagsDTO(tags));
        }
        return tagsDTO;
    }

}
