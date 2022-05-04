package ru.mipt.data.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.mipt.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository interface help interact with database
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * This method find User by username
     *
     * @param username User's username
     * @return User
     */
    User findByUsername(String username);

    /**
     * This method find User by id
     *
     * @param id User's id
     * @return User
     */
    User findUserById(int id);

    /**
     * This method find User by email
     *
     * @param email User's email
     * @return User
     */
    User findUserByEmail(String email);

    /**
     * This method add user's favorite workout
     *
     * @param userId    for add
     * @param workoutId for add
     */
    @Modifying
    @Query(value = "INSERT INTO users_favorite_workouts  (users_id,workouts_id) VALUES (?1,?2)",
            nativeQuery = true)
    void addFavoriteWorkouts(int userId, int workoutId);

    /**
     * This method remove user's favorite workout
     *
     * @param userId    for remove
     * @param workoutId for remove
     */
    @Modifying
    @Query(value = "DELETE FROM users_favorite_workouts WHERE users_id = ?1 AND workouts_id = ?2",
            nativeQuery = true)
    void removeFavoriteWorkouts(int userId, int workoutId);

    @Modifying
    @Query(value = "UPDATE User SET username = ?2, firstname = ?3, lastname = ?4, email = ?5 where id=?1")
    void updateUserInfo(int userId, String username, String firstname, String lastName, String email);

}