
package com.example.data.controller;

import com.example.data.model.User;
import com.example.data.repository.UserRepository;
import com.example.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;

import static com.example.data.Role.USER;

public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private static UserRepository userRepository;


    public static boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(USER));
//        user.setHashedPassword(user.getHashedPassword());
        userRepository.save(user);
        return true;
    }

}