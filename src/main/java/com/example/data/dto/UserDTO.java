package com.example.data.dto;
import com.example.data.model.User;
import com.example.data.model.Workout;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Main Bean class that we build the form for.
 * <p>
 * Uses Bean Validation (JSR-303) annotations for automatic validation.
 */
public class UserDTO {

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
    // @Size(min = 8, max = 64, message = "Password must be 8-64 char long")
    private String password;

    private List<Workout> favoriteTrainings = new ArrayList<Workout>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPasswordPassword() {
        return password;
    }

    public void setHashedPassword(String password) {
        this.password = password;
    }

    //public boolean isAllowsMarketing() {
        //return allowsMarketing;
    //}

    //public void setAllowsMarketing(boolean allowsMarketing) {
        //this.allowsMarketing = allowsMarketing;
    //}

    public ArrayList<Workout> getFavoriteTrainings() {
        return (ArrayList<Workout>) favoriteTrainings;
    }

    public void setFavoriteTrainings(ArrayList<Workout> favoriteTrainings) {
        this.favoriteTrainings = favoriteTrainings;
    }

    public User fromWithoutRoles() {
        return new User();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO that = (UserDTO) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}