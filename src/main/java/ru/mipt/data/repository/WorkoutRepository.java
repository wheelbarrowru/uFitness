package ru.mipt.data.repository;

import ru.mipt.data.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * WorkoutRepository interface help interact with database
 */
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    /**
     * This method update rating and count of voices of workout
     * @param id workout's id
     * @param rating workout's rating
     * @param count amount of user's grades
     */
    @Modifying
    @Query(value = "UPDATE Workout set rating=?2, countVote = ?3 WHERE id=?1")
    void updateRatingAndCount(int id, double rating, int count);

}

