package com.example.data.controller;

import com.example.data.model.User;
import com.example.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProfileController {
    private UserService userService;

    @Autowired
    public ProfileController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/profile")
    public Optional<User> profile(int id){
        System.out.println();
         return this.userService.get(id);
    }



}