package com.example.data.repository;

import com.example.data.model.User;
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

}