package com.example.data.controller;

import com.example.data.dto.UserDTO;
import com.example.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {
    private UserService userService;

    @Autowired
    public ProfileController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/profile/data/{id}")
    public @ResponseBody UserDTO getProfile(@RequestHeader HttpHeaders headers, @PathVariable int id){
        List<String> values =  headers.getValuesAsList("USER_AGENT");
        if (values.contains("admin")){
            return this.userService.getDTO(id);
        }
        else {
            return new UserDTO();
        }
    }

}