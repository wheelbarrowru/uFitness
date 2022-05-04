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

import java.util.*;
import java.util.function.Function;

class TagsServiceTest {
    public ArrayList<Optional<Tags>> arraylist = new ArrayList<Optional<Tags>>();
    @InjectMocks
    private TagsRepository tagsRepository = new TagsRepository() {
        @Override
        public Tags findByMessage(String message) {
            Tags tags = new Tags();
            tags.setId(0);
            tags.setMessage("test");
            HashSet<Workout> hashset = new HashSet<Workout>();
            tags.setWorkouts(hashset);
            return message == "test" ? tags : null;
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
            Tags tags = entity;
            return (S) tags;
        }

        @Override
        public Optional<Tags> findById(Integer integer) {
            Tags tags = new Tags();
            tags.setMessage("test");
            tags.setId(0);
            HashSet<Workout> hashset = new HashSet<Workout>();
            tags.setWorkouts(hashset);
            return integer == 0 ? Optional.of(tags) : Optional.empty();
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
            arraylist.add(tagsRepository.findById(0));
            arraylist.remove(tagsRepository.findById(integer));
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
    void get() {
        Tags tags = new Tags();
        tags.setMessage("test");
        tags.setId(0);
        HashSet<Workout> hashset = new HashSet<Workout>();
        tags.setWorkouts(hashset);
        Assertions.assertEquals(Optional.of(tags), tagsService.get(0));
    }

    @Test
    void getDTO() {
        TagsDTO tagsDTO = new TagsDTO(0, "test");
        TagsDTO tags = new TagsDTO(0, null);
        Assertions.assertEquals(TagsService.convertToTagsDTO(tagsService.get(0).orElse(new Tags())), tagsDTO);
        Assertions.assertEquals(TagsService.convertToTagsDTO(tagsService.get(222).orElse(new Tags())), tags);
    }

    @Test
    void update() {
        Tags tags = new Tags();
        tags.setMessage("test");
        tags.setId(0);
        HashSet<Workout> hashset = new HashSet<Workout>();
        tags.setWorkouts(hashset);
        TagsDTO tagsDTO = new TagsDTO(0, "test");
        Assertions.assertEquals(tags, tagsService.update(tags));
    }

    @Test
    void delete() {
        tagsRepository.deleteById(0);
        ArrayList<User> ArrayList = new ArrayList<User>();
        Assertions.assertEquals(ArrayList, arraylist);
    }

    @Test
    void count() {
        Assertions.assertEquals(0, tagsService.count());
    }

    @Test
    void convertToTags() {
        TagsDTO tagsDTO = new TagsDTO(0, "test");
        Tags tags = new Tags();
        tags.setId(0);
        tags.setMessage("test");
        HashSet<Workout> hashset = new HashSet<Workout>();
        tags.setWorkouts(hashset);
        Assertions.assertEquals(TagsService.convertToTags(tagsDTO), tags);
    }

    @Test
    void convertToTagsDTO() {
        TagsDTO tagsDTO = new TagsDTO(0, "test");
        Tags tags = new Tags();
        tags.setId(0);
        tags.setMessage("test");
        HashSet<Workout> hashSet = new HashSet<Workout>();
        tags.setWorkouts(hashSet);
        Assertions.assertEquals(TagsService.convertToTagsDTO(tags), tagsDTO);
    }
    @Test
    void getDTOByMessage() {
        String message = "test";
        TagsDTO tagsDTO = new TagsDTO(0, "test");
        TagsDTO empty = new TagsDTO(0, null);
        Assertions.assertEquals(TagsService.getDTOByMessage(message), tagsDTO);
        Assertions.assertEquals(TagsService.getDTOByMessage("aaaaaaaaaaa"), empty);
    }
}