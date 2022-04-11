package com.example.data.repository;

import com.example.data.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Integer> {

    Tags findByMessage(String message);
}
