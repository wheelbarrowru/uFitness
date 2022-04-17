package com.example.data.controller;

import com.example.data.dto.UserDTO;
import com.example.data.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Api(value="GameOfThronesService")
public class ProfileController {
    private final UserService userService;
    @Value("${server.port}")
    private String serverPort;
    final String url = "http://localhost:" + serverPort + "/";


    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/profile/data/{id}"})
    @ApiOperation(value = "Get Profile", notes = "Returns all the profile data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public @ResponseBody
    ResponseEntity<UserDTO> getProfile(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        List<String> values = headers.getValuesAsList("USER_AGENT");
        if (values.contains("admin")) {
            return new ResponseEntity<>(this.userService.getDTO(id),HttpStatus.OK) ;
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
        }

    }

}
