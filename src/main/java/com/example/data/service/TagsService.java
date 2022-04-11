package com.example.data.service;

import com.example.data.dto.TagsDTO;
import com.example.data.model.Tags;
import com.example.data.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TagsService {
    private static TagsRepository tagsRepository;
    @Autowired
    public TagsService(TagsRepository repository) {
        tagsRepository = repository;
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

    public Set<TagsDTO> getSetOfDTO() {
        Set<TagsDTO> tagsDTOSet = new HashSet<>();
        Set<Tags> tags = new HashSet<>(tagsRepository.findAll());
        for (Tags tag: tags){
            tagsDTOSet.add(convertToTagsDTO(tag));
        }
        return tagsDTOSet;
    }

    public int count() {
        return (int) tagsRepository.count();
    }

    protected static Tags convertToTags(TagsDTO tagsDTO){
        return tagsRepository.findByMessage(tagsDTO.getMessage());
    }
    protected static TagsDTO convertToTagsDTO(Tags tags){
        TagsDTO tagsDTO = new TagsDTO(tags.getId(), tags.getMessage());
        return tagsDTO;
    }
}
