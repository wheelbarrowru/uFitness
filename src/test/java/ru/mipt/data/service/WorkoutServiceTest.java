package ru.mipt.data.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import ru.mipt.data.dto.TagsDTO;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.model.Tags;
import ru.mipt.data.model.User;
import ru.mipt.data.model.Workout;
import ru.mipt.data.repository.TagsRepository;
import ru.mipt.data.repository.WorkoutRepository;

import java.util.*;
import java.util.function.Function;

class WorkoutServiceTest {
    public ArrayList<Optional<Workout>> arraylist = new ArrayList<Optional<Workout>>();
    @InjectMocks
    private WorkoutRepository workoutRepository = new WorkoutRepository() {
        @Override
        public void updateRatingAndCount(int id, double rating, int count) {

        }

        @Override
        public Set<Workout> findByAuthorId(int authorId) {
            return null;
        }

        @Override
        public Integer findVotedUserId(int workoutId, int userId) {
            return 0;
        }

        @Override
        public void addVotedUsersId(int workoutId, int userId, int userVote) {

        }

        @Override
        public void removeVotedUsersId(int workoutId, int userId) {

        }

        @Override
        public List<Workout> findAll() {
            return null;
        }

        @Override
        public List<Workout> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<Workout> findAllById(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public <S extends Workout> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Workout> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Workout> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Workout> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Integer> integers) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Workout getOne(Integer integer) {
            return null;
        }

        @Override
        public Workout getById(Integer integer) {
            return null;
        }

        @Override
        public <S extends Workout> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Workout> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<Workout> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Workout> S save(S entity) {
            Workout workout = entity;
            return (S) workout;
        }

        @Override
        public Optional<Workout> findById(Integer integer) {
            Workout workout = new Workout();
            workout.setId(0);
            workout.setRating(5);
            workout.setDescription("test");
            workout.setAuthorId(0);
            workout.setTitle("test");
            HashSet<Tags> hashSet = new HashSet<>();
            workout.setWorkoutTags(hashSet);
            return integer == 0 ? Optional.of(workout) : Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {
            arraylist.remove(workoutRepository.findById(integer));

        }

        @Override
        public void delete(Workout entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers) {

        }

        @Override
        public void deleteAll(Iterable<? extends Workout> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Workout> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Workout> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Workout> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Workout> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Workout, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    };
    @Autowired
    private WorkoutService workoutService = new WorkoutService(workoutRepository);


    @Test
    void get() {
        Workout workout = new Workout();
        workout.setId(0);
        workout.setRating(5);
        workout.setDescription("test");
        workout.setAuthorId(0);
        workout.setTitle("test");
        HashSet<Tags> hashSet = new HashSet<>();
        workout.setWorkoutTags(hashSet);
        Assertions.assertEquals(Optional.of(workout), workoutService.get(0));
    }

    @Test
    void getDTO() {
        WorkoutDTO workoutDTO = new WorkoutDTO(0, "test", "test", 5, 0, new HashSet<TagsDTO>());
        WorkoutDTO workout = new WorkoutDTO();
        Assertions.assertEquals(workoutService.getDTO(0), workoutDTO);
        Assertions.assertNull(workoutService.getDTO(222));
    }

    @Test
    void update() {
        Workout workout = new Workout();
        workout.setId(0);
        workout.setRating(5);
        workout.setDescription("test");
        workout.setTitle("test");
        workout.setAuthorId(0);
        HashSet<Tags> hashSet = new HashSet<>();
        workout.setWorkoutTags(hashSet);
        WorkoutDTO workoutDTO = new WorkoutDTO(0, "test", "test", 5, 0, new HashSet<TagsDTO>());
        Assertions.assertEquals(workout, workoutService.update(workoutDTO));
    }

    @Test
    void delete() {
        arraylist.add(workoutRepository.findById(0));
        workoutService.delete(0);
        ArrayList<User> ArrayList = new ArrayList<>();
        Assertions.assertEquals(ArrayList, arraylist);
    }

    @Test
    void count() {
        Assertions.assertEquals(0, workoutService.count());
    }

    //TODO update
    @Test
    void updateRating() {
        int value = Integer.parseInt("5");
        Workout workout = new Workout();
        workout.setId(0);
        workout.setRating(4);
        workout.setDescription("test");
        workout.setTitle("test");
        workout.setAuthorId(0);
        HashSet<Tags> hashSet = new HashSet<>();
        workout.setWorkoutTags(hashSet);

        workoutService.updateRating(0, 0, "3");
        Assertions.assertEquals(workoutRepository.findById(0), workout.getRating());


    }

    @Test
    void convertToWorkoutDTO() {
        Workout workout = new Workout();
        workout.setId(0);
        workout.setRating(5);
        workout.setDescription("test");
        workout.setTitle("test");
        workout.setAuthorId(0);
        HashSet<Tags> hashSet = new HashSet<>();
        workout.setWorkoutTags(hashSet);
        WorkoutDTO workoutDTO = new WorkoutDTO(0, "test", "test", 5, 0, new HashSet<TagsDTO>());
        Assertions.assertEquals(WorkoutService.convertToWorkoutDTO(workout), workoutDTO);
    }

    @InjectMocks
    private TagsRepository tagsRepository = new TagsRepository() {
        @Override
        public Tags findByMessage(String message) {
            Tags tags = new Tags();
            tags.setId(0);
            tags.setMessage("test");
            HashSet<Workout> hashset = new HashSet<Workout>();
            tags.setWorkouts(hashset);
            return tags;
        }

        @Override
        public List<Tags> findAll() {
            return null;
        }

        @Override
        public List<Tags> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<Tags> findAllById(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public <S extends Tags> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Tags> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Tags> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Tags> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Integer> integers) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Tags getOne(Integer integer) {
            return null;
        }

        @Override
        public Tags getById(Integer integer) {
            return null;
        }

        @Override
        public <S extends Tags> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Tags> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<Tags> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Tags> S save(S entity) {
            return null;
        }

        @Override
        public Optional<Tags> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(Tags entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers) {

        }

        @Override
        public void deleteAll(Iterable<? extends Tags> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Tags> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Tags> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Tags> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Tags> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Tags, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    };
    @Autowired
    private TagsService tagsService = new TagsService(tagsRepository);

    @Test
    void convertToTagsSet() {
        Set<TagsDTO> tagsDTO = new HashSet<>();
        Set<Tags> tags1 = new HashSet<>();
        Tags tags = new Tags();
        tags.setId(0);
        tags.setMessage("test");
        HashSet<Workout> hashset = new HashSet<Workout>();
        tags.setWorkouts(hashset);
        tagsDTO.add(TagsService.convertToTagsDTO(tags));
        tags1.add(tags);
        Assertions.assertEquals(WorkoutService.convertToTagsSet(tagsDTO), tags1);
    }

    @Test
    void convertToWorkoutDTOSet() {
        Set<Workout> workout = new HashSet<>();
        Set<WorkoutDTO> workout1 = new HashSet<>();
        Workout workout2 = new Workout();
        workout2.setId(0);
        workout2.setRating(5);
        workout2.setAuthorId(0);
        workout2.setDescription("test");
        workout2.setTitle("test");
        HashSet<Tags> hashSet = new HashSet<>();
        workout2.setWorkoutTags(hashSet);
        workout.add(workout2);
        WorkoutDTO workout3 = new WorkoutDTO(0, "test", "test", 5, 0, new HashSet<TagsDTO>());
        workout1.add(workout3);
        Assertions.assertEquals(WorkoutService.convertToWorkoutDTOSet(workout), workout1);
    }

    @Test
    void getVotedUserId() {
        int workoutId = 0;
        int userId = 0;
        Assertions.assertEquals(workoutService.getVotedUserId(workoutId, userId), 0);
    }
}