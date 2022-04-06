package com.example.data.dto;

import com.example.data.Role;
import com.example.data.model.User;
import com.example.data.model.Workout;
import com.example.data.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static com.example.data.Role.USER;

public class SignUpRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String mail;
    private String hashedPassword;
    private String passwordchecker;
    private Set<Role> roles;
    //private String profilePictureUrl = "";
    private ArrayList<Workout> favoriteTrainings = new ArrayList<Workout>();




    public void fromWithoutRoles() {
        while (!passwordchecker.equals(hashedPassword))
            System.out.println("passwords do not match");
        User user = new User();
        user.setMail(mail);
        user.setHashedPassword(hashedPassword);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUsername(username);
        //user.setProfilePictureUrl(profilePictureUrl);
        user.setRoles(roles);
        user.setId(UUID.randomUUID());
    }
}

