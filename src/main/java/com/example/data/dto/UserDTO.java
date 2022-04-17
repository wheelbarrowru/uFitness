package com.example.data.dto;

import com.example.data.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
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
@ApiModel(value = "UserDTO", description = "Complete data of an userDTO")
@NoArgsConstructor
public class UserDTO {
    /**
     * User's id
     */
    @NotBlank
    @Schema(description = "User id")
    private int id;

    /**
     * User's username
     */
    @NotBlank
    @Schema(description = "Username")
    private String username;

    /**
     * User's firstname
     */
    @NotBlank
    @Schema(description = "User first name")
    private String firstName;

    /**
     * User's lastname
     */
    @NotBlank
    @Schema(description = "User last name")
    private String lastName;

    /**
     * User's email
     */
    @NotBlank
    @Email
    @Schema(description = "User email")
    private String email;

    /**
     * User's roles
     */
    @NotBlank
    @Schema(description = "User role in system")
    private Set<Role> roles;

    /**
     * Is user allows marketing or no
     */
    @Schema(description = "User allows for marketing")
    private boolean allowsMarketing;

    /**
     * User's password
     */
    @Size(min = 8, max = 64, message = "Password must be 8-64 char long")
    @Schema(description = "User encrypted password")
    private String password;

    /**
     * Constructor method for UserDTO
     * @param id User's id
     * @param username User's username
     * @param firstName User's firstname
     * @param lastName User's lastname
     * @param email User's email
     * @param password User's password
     */
    /*
    private List<Workout> favoriteTrainings = new ArrayList<Workout>();

    */
    public UserDTO(int id, String username, String firstName, String lastName,
                   String email, String password) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


}