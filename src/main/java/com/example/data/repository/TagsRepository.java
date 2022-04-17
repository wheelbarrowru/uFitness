package com.example.data.repository;

import com.example.data.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TagsRepository interface
 */
@Repository
public interface TagsRepository extends JpaRepository<Tags, Integer> {

    /**
     * This method find Tags by Message
     * @param message tags description message
     * @return Tags
     */
    Tags findByMessage(String message);
}
