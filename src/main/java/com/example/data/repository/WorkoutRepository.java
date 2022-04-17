package com.example.data.repository;

import com.example.data.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * WorkoutRepository interface
 */
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    /**
     * This method update rating of workout
     * @param id workout id
     * @param rating workout rating
     * @param count amount of user's grades
     */
    @Modifying
    @Query(value = "UPDATE Workout set rating=?2, countVote = ?3 WHERE id=?1")
    void updateRatingAndCount(int id, double rating, int count);

}

