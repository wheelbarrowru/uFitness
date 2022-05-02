package ru.mipt.data.repository;

import ru.mipt.data.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * WorkoutRepository interface help interact with database
 */
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    /**
     * This method update rating and count of voices of workout
     *
     * @param id     workout's id
     * @param rating workout's rating
     * @param count  amount of user's grades
     */
    @Modifying
    @Query(value = "UPDATE Workout set rating=?2, countVote = ?3 WHERE id=?1")
    void updateRatingAndCount(int id, double rating, int count);

    /**
     * Searching workouts by authorID
     *
     * @param authorId for search
     * @return set of workouts
     */
    Set<Workout> findByAuthorId(int authorId);

    /**
     * Get voted users' id
     *
     * @param workoutId for search
     * @return set of voted user id
     */
    @Query(value = "SELECT user_vote FROM workouts_voted_users WHERE workout_id = ?1 AND user_id = ?2",
            nativeQuery = true)
    Integer findVotedUserId(int workoutId, int userId);

    /**
     * add voted users' id
     *
     * @param workoutId for add
     * @param userId    for add
     * @param userVote  for add
     */
    @SuppressWarnings("SqlInsertValues")
    @Modifying
    @Query(value = "INSERT INTO workouts_voted_users (workout_id,user_id,user_vote) VALUES (?1,?2,?3)",
            nativeQuery = true)
    void addVotedUsersId(int workoutId, int userId, int userVote);

    /**
     * remove voted users' id
     *
     * @param workoutId for remove
     * @param userId    for remove
     */
    @Modifying
    @Query(value = "DELETE FROM workouts_voted_users WHERE workout_id = ?1 AND user_id = ?2",
            nativeQuery = true)
    void removeVotedUsersId(int workoutId, int userId);

}

