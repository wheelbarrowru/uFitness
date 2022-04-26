package ru.mipt.data.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mipt.data.dto.UserDTO;
import ru.mipt.data.service.UserService;

import java.net.URI;
import java.util.List;

/**
 * Controller class for connecting User and front of Profile page
 */
@RestController
@Api(value = "Profile Data Controller", tags = "Profile Data Controller")
public class ProfileController {
    @Setter
    private UserService userService;

    /**
     * Constructor of ProfileController
     *
     * @param userService User's userServoce
     */
    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This method for redirecting user on his Profile page
     *
     * @param headers HttpHeaders
     * @param id      User's id
     * @return User's profile page if id is correct and start-page if user's id isn't correct
     * @see UserService#getDTO(int)
     */
    @GetMapping({"/profile/data/{id}"})
    @ApiOperation(value = "Get Profile Data", notes = "Returns all the profile data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved UserDTO"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public @ResponseBody
    ResponseEntity<UserDTO> getProfile(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        List<String> values = headers.getValuesAsList("USER_AGENT");
        if (values.contains("admin")) {
            return new ResponseEntity<>(this.userService.getDTO(id), HttpStatus.OK);
        } else {
            final String url =
                    ServletUriComponentsBuilder.fromCurrentRequestUri().replacePath(null).build().toUriString();
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(URI.create(url)).build();
        }

    }

}
