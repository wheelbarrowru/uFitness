package com.example.data.dto;
import com.example.data.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Main Bean class that we build the form for.
 * <p>
 * Uses Bean Validation (JSR-303) annotations for automatic validation.
 */
@Data
@AllArgsConstructor
@ApiModel(value = "User entity", description = "Complete data of an entity user")
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    @ApiModelProperty(notes = "The database generated user's ID")
    private int id;

    @NotBlank
    @ApiModelProperty(notes = "User's username", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(notes = "User's firstname", required = true)
    private String firstName;

    @NotBlank
    @ApiModelProperty(notes = "User's lastname", required = true)
    private String lastName;

    @NotBlank
    @Email
    @ApiModelProperty(notes = "User's email", required = true)
    private String email;

    @NotBlank
    @ApiModelProperty(notes = "User's Role", required = true)
    private Set<Role> roles;

    private boolean allowsMarketing;

    // FIXME Passwords should never be stored in plain text!
    @Size(min = 8, max = 64, message = "Password must be 8-64 char long")
    @ApiModelProperty(notes = "User's password", required = true)
    private String password;
    /*
    private List<Workout> favoriteTrainings = new ArrayList<Workout>();

    */
    public UserDTO(int id, String username, String firstName, String lastName,
                   String email, String password){
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


}