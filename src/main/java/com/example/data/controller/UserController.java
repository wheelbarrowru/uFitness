package com.example.data.controller;

import com.example.data.repository.UserRepository;
import com.example.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/registration")
public class UserController {

    public final UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService=userService; }

}
