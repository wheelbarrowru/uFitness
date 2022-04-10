package com.example.data.service;

import com.example.data.dto.TagsDTO;
import com.example.data.model.Tags;
import com.example.data.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagsService {
    private final TagsRepository tagsRepository;
    @Autowired
    public TagsService(TagsRepository repository) {
        this.tagsRepository = repository;
    }

    public Optional<Tags> get(int id) {
        return tagsRepository.findById(id);
    }
    //FIXME
    public TagsDTO getDTO(int id) { return convertToTagsDTO(get(id).orElse(new Tags())); }

    public Tags update(Tags entity) {
        return tagsRepository.save(entity);
    }

    public void delete(int id) {
        tagsRepository.deleteById(id);
    }

    public Page<Tags> list(Pageable pageable) {
        return tagsRepository.findAll(pageable);
    }

    public int count() {
        return (int) tagsRepository.count();
    }

    protected static Tags convertToTags(TagsDTO tagsDTO){
        Tags tags = new Tags();
        tags.setMessage(tagsDTO.getMessage());
        //TODO add workouts
        return tags;
    }
    protected static TagsDTO convertToTagsDTO(Tags tags){
        TagsDTO tagsDTO = new TagsDTO(tags.getId(), tags.getMessage());
        return tagsDTO;
    }
}
