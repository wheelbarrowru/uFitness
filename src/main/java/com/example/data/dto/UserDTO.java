package com.example.data.dto;
import com.example.data.Role;
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
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    private int id;

    @NotBlank
    private String username;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private Set<Role> roles;

    private boolean allowsMarketing;

    // FIXME Passwords should never be stored in plain text!
    @Size(min = 8, max = 64, message = "Password must be 8-64 char long")
    private String password;
    /*
    private List<Workout> favoriteTrainings = new ArrayList<Workout>();

    */


}