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
import java.util.Optional;
import java.util.Set;
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

    @Transactional
    public void updateRating(WorkoutDTO workoutDTO, String valueString){
        int value = Integer.parseInt(valueString);
        Workout workout = workoutRepository.findById(workoutDTO.getId()).get();
        int count = workout.getCountVote();
        workoutRepository.updateRatingAndCount(workoutDTO.getId(), (double)Math.round(100*(workout.getRating()*count+value)/(count+1))/100, value );

    }

    private Workout convertToWorkout(WorkoutDTO workoutDTO){
        Workout workout = new Workout();
        workout.setTitle(workoutDTO.getTitle());
        workout.setDescription(workoutDTO.getDescription());
        workout.setRating(workoutDTO.getRating());
        workout.setWorkoutTags(convertToTagsSet(workoutDTO.getWorkoutTags()));

        return workout;
    }
    private WorkoutDTO convertToWorkoutDTO(Workout workout){
        return new WorkoutDTO(workout.getId(),
                workout.getTitle(),
                workout.getDescription(),
                workout.getRating(),
                convertToTagsDTOSet(workout.getWorkoutTags()));
    }
    private Set<Tags> convertToTagsSet(Set<TagsDTO> tagsDTOSet){
        Set<Tags> tags = new HashSet<>();
        for(TagsDTO tagsDTO: tagsDTOSet){
            tags.add(TagsService.convertToTags(tagsDTO));
        }
        return tags;
    }
    private Set<TagsDTO> convertToTagsDTOSet(Set<Tags> tagsSet){
        Set<TagsDTO> tagsDTO = new HashSet<>();
        for(Tags tags: tagsSet){
            tagsDTO.add(TagsService.convertToTagsDTO(tags));
        }
        return tagsDTO;
    }

}
