package com.example.ufiness.vaadin.data.service;

import com.example.ufiness.vaadin.data.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);
}