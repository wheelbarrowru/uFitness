package com.example.data.repository;

import com.example.data.model.User;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //@Query("select User from User where User.username=?1")
    User findByUsername(String username);

    User findUserById(int id);

    User findUserByEmail(String email);

}