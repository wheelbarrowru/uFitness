
package com.example.data.controller;

import com.example.data.model.User;
import com.example.data.model.Workout;
import com.example.data.repository.UserRepository;
import com.example.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.example.data.Role.USER;

//@RestController
//@RequestMapping(path = "/registration")
public class RegistrationController {
    @Autowired
    public static UserService userService;
//
//    @Autowired
//    private static UserRepository userRepository;


    public static boolean saveUser(User user) {
        ArrayList<ArrayList<String>> listOfUsers = new ArrayList<ArrayList<String>>();
        ArrayList<String> secondList = new ArrayList<String>();
        //secondList.add(user.getId().toString());
        secondList.add(user.getFirstname().toString());
        secondList.add(user.getLastname().toString());
        secondList.add(user.getUsername());
        secondList.add(user.getEmail().toString());
        //secondList.add(user.getHashedPassword().toString());
        listOfUsers.add(secondList);
        return true;
    }

}