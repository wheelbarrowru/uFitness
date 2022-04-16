package com.example.data.controller;

import com.example.data.dto.UserDTO;
import com.example.data.service.UserService;
import com.vaadin.flow.server.VaadinServlet;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URI;
import java.util.List;

@RestController
public class ProfileController {
    private UserService userService;
    @Value("${server.port}")
    private String serverPort;
    final String url = "http://localhost:" + serverPort + "/";


    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/profile/data/{id}"})
    @ApiOperation(value = "Get Profile", notes = "Returns all the profile data")
    @ApiResponses({@ApiResponse(code = 200, message = "Returns users profile information")})
    public @ResponseBody
    UserDTO getProfile(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        List<String> values = headers.getValuesAsList("USER_AGENT");
        if (values.contains("admin")) {
            return this.userService.getDTO(id);
        } else {
            Redirect();
            return new UserDTO();
        }

    }

    public ResponseEntity<Object> Redirect() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    }
}
