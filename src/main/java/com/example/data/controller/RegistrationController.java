
package com.example.data.controller;

import com.example.data.dto.UserDTO;
import com.example.data.model.User;
import com.example.data.model.Workout;
import com.example.data.repository.UserRepository;
import com.example.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.example.data.Role.USER;

@RestController
@RequestMapping(path = "/registration")
public class RegistrationController {

    public final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) { this.userService=userService; }

    @PostMapping(value = "/registration")
    public void addUser(@RequestBody UserDTO userDTO){
        userService.update(userDTO);
    }
}