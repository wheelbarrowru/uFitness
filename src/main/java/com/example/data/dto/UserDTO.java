package com.example.data.dto;
import com.example.data.model.User;
import com.example.data.model.Workout;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//import java.util.UUID;

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

    private boolean allowsMarketing;

    // FIXME Passwords should never be stored in plain text!
    @Size(min = 8, max = 64, message = "Password must be 8-64 char long")
    private String password;

    public UserDTO(int id, String username, String firstName, String lastName,
                   String email, String password){
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    /*
    private List<Workout> favoriteTrainings = new ArrayList<Workout>();



    public ArrayList<Workout> getFavoriteTrainings() {
        return (ArrayList<Workout>) favoriteTrainings;
    }

    public void setFavoriteTrainings(ArrayList<Workout> favoriteTrainings) {
        this.favoriteTrainings = favoriteTrainings;
    }

    */


}