package ru.mipt.data.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.model.Tags;
import ru.mipt.data.model.Workout;
import ru.mipt.data.repository.WorkoutRepository;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

class FindWorkoutsServiceTest {
    @InjectMocks
    @SuppressWarnings({"all"})
    private final WorkoutRepository workoutRepository = new WorkoutRepository() {

        @Override
        public List<Workout> findAll() {
            ArrayList<Workout> list = new ArrayList<>();
            Workout workout = new Workout();
            workout.setId(0);
            workout.setRating(5);
            workout.setDescription("test");
            workout.setTitle("test");
            workout.setAuthorId(0);
            HashSet<Tags> hashSet = new HashSet<>();
            workout.setWorkoutTags(hashSet);
            Workout workout1 = new Workout();
            workout1.setId(1);
            workout1.setRating(5);
            workout1.setDescription("test1");
            workout1.setTitle("test1");
            workout1.setAuthorId(0);
            workout1.setWorkoutTags(hashSet);
            list.add(workout);
            list.add(workout1);
            return list;
        }

        @Override
        public List<Workout> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<Workout> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<Workout> findAllById(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

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
        public <S extends Workout> S save(S entity) {
            return null;
        }

        @Override
        public <S extends Workout> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<Workout> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
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
        public <S extends Workout> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
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

        @Override
        public void updateRatingAndCount(int id, double rating, int count) {

        }

        @Override
        public Set<Workout> findByAuthorId(int authorId) {
            Set<Workout> set = new HashSet<>();
            Workout workout1 = new Workout();
            workout1.setId(1);
            workout1.setRating(5);
            workout1.setDescription("test1");
            workout1.setTitle("test1");
            workout1.setAuthorId(0);
            HashSet<Tags> hashSet = new HashSet<>();
            workout1.setWorkoutTags(hashSet);
            set.add(workout1);
            return set;
        }

        @Override
        public Integer findVotedUserId(int workoutId, int userId) {
            return null;
        }

        @Override
        public void addVotedUsersId(int workoutId, int userId, int userVote) {

        }

        @Override
        public void removeVotedUsersId(int workoutId, int userId) {

        }
    };

    private final FindWorkoutsService findWorkoutsService = new FindWorkoutsService(workoutRepository);

    @Test
    void findWorkoutsByTitleAndTagsDTOTest() {
        List<Workout> workouts = workoutRepository.findAll();
        System.out.println(workouts);
        Set<WorkoutDTO> resultSet = new HashSet<>();
        Pattern pattern = Pattern.compile(String.format(".*%s.*", "test1"));
        for (Workout workout :
                workouts) {
            if (workout.getWorkoutTags().containsAll(new HashSet<Tags>()) && pattern.matcher(workout.getTitle()).matches()) {
                resultSet.add(WorkoutService.convertToWorkoutDTO(workout));
            }
        }
        System.out.println(resultSet);
        Assertions.assertEquals(findWorkoutsService.findWorkoutsByTitleAndTagsDTO("test1", new ArrayList<>()), resultSet);
    }

    @Test
    void findWorkoutByAuthorId() {
        Set<WorkoutDTO> set = new HashSet<>();
        WorkoutDTO workoutDTO = new WorkoutDTO(1, "test1", "test1", 5, 0, new HashSet<>());
        set.add(workoutDTO);
        Assertions.assertEquals(findWorkoutsService.findWorkoutByAuthorId(0), set);
    }
}