package ru.mipt.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.data.dto.TagsDTO;
import ru.mipt.data.model.Tags;
import ru.mipt.data.repository.TagsRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * TagsService class is responsible for interacting with tagsDTO and Tags with <b>TagsRepository</b>
 */
@Service
public class TagsService {
    private static TagsRepository tagsRepository;

    /**
     * Constructor of TagsService
     *
     * @param repository tagsRepository
     * @see TagsRepository
     */
    @Autowired
    public TagsService(TagsRepository repository) {
        tagsRepository = repository;
    }

    /**
     * Method for getting Tags by id
     *
     * @param id Tag's id
     * @return tags
     * @see TagsRepository#findById(Object)
     */
    public Optional<Tags> get(int id) {
        return tagsRepository.findById(id);
    }

    /**
     * Method to get converted Tags into TagsGTO by id
     * <p> Return empty TagsDTO if tag hasn't found</p>
     *
     * @param id Tags id
     * @return TagsDTO
     * @see TagsService#convertToTagsDTO(Tags)
     */
    public TagsDTO getDTO(int id) {
        return convertToTagsDTO(get(id).orElse(new Tags()));
    }


    /**
     * Method to get converted Tags into TagsGTO by message
     * <p> Return empty TagsDTO if tag hasn't found</p>
     *
     * @param message name
     * @return TagsDTO
     */
    public static TagsDTO getDTOByMessage(String message) {
        return tagsRepository.findByMessage(message) != null ? convertToTagsDTO(tagsRepository.findByMessage(message)) : new TagsDTO(0, null);
    }

    /**
     * Method for saving changes in Tags
     *
     * @param entity Tags
     * @return tagsRepository.save(entity)
     * @see TagsRepository#save(Object)
     */
    public Tags update(Tags entity) {
        return tagsRepository.save(entity);
    }

    /**
     * Method for deleting tags
     *
     * @param id tagsRepository's id
     * @see TagsRepository#deleteById(Object)
     */
    public void delete(int id) {
        tagsRepository.deleteById(id);
    }

    /**
     * Method for making Set of TagsDTO
     *
     * @return Set<tagsDTO>
     * @see TagsRepository#findAll()
     * @see TagsService#convertToTagsDTO(Tags)
     */
    public Set<TagsDTO> getSetOfDTO() {
        Set<TagsDTO> tagsDTOSet = new HashSet<>();
        Set<Tags> tags = new HashSet<>(tagsRepository.findAll());
        for (Tags tag : tags) {
            tagsDTOSet.add(convertToTagsDTO(tag));
        }
        return tagsDTOSet;
    }

    /**
     * Method for counting tags
     *
     * @return amount of tags
     * @see TagsRepository#count()
     */
    public int count() {
        return (int) tagsRepository.count();
    }

    /**
     * This method convert TagsDTO into Tags
     *
     * @param tagsDTO tagsDTO
     * @return tagsRepository
     * @see TagsRepository#findByMessage(String)
     */
    protected static Tags convertToTags(TagsDTO tagsDTO) {
        return tagsRepository.findByMessage(tagsDTO.getMessage());
    }

    /**
     * This method convert Tags into TagsDTO
     *
     * @param tags Tags
     * @return TagsDTO
     * @see Tags#getMessage()
     * @see TagsDTO#getId()
     */
    protected static TagsDTO convertToTagsDTO(Tags tags) {
        return new TagsDTO(tags.getId(), tags.getMessage());
    }
}
