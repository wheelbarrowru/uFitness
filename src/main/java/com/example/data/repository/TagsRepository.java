package com.example.data.repository;

import com.example.data.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TagsRepository interface help interact with database
 */
@Repository
public interface TagsRepository extends JpaRepository<Tags, Integer> {

    /**
     * This method find Tags by name
     *
     * @param message tags description message
     * @return Tags
     */
    Tags findByMessage(String message);
}
