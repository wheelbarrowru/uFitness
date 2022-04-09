package com.example.data.model;

import com.example.data.Role;
import com.example.data.controller.RegistrationController;
import com.example.data.repository.UserRepository;
import com.example.data.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "application_users")
public class User extends AbstractEntity {
    //тут добавь поля, которые нам нужны будут, к ним геттеры и сеттеры (используй генератор)
    //все что ниже нагенерировано vaadin. Пока не трогай

    @Column(name = "firstname", insertable = false, updatable = false)
    private String firstname;
    @Column(name = "lastname", insertable = false, updatable = false)
    private String lastname;
    @Column(name = "username", insertable = false, updatable = false)
    private String username;
    @Column(name = "email", insertable = false, updatable = false)
    private String email;

    @Column(name = "id", insertable = false, updatable = false)
    private UUID Id = getId();

    @Column(name = "hashed_password")
    private String password;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @OneToMany(mappedBy = "description", cascade = ALL)
    private List<Workout> favoriteTrainings = new ArrayList<Workout>();
    // @Lob
    //private String profilePictureUrl;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return password;
    }

    public void setHashedPassword(String hashedPassword) {
        this.password = hashedPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

   // public String getProfilePictureUrl() {
        //return profilePictureUrl;
    //}

    //public void setProfilePictureUrl(String profilePictureUrl) {
        //this.profilePictureUrl = profilePictureUrl;
    //}

    public ArrayList<Workout> getFavoriteTrainings() {
        return (ArrayList<Workout>) favoriteTrainings;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFavoriteTrainings(ArrayList<Workout> favoriteTrainings) {
        this.favoriteTrainings = favoriteTrainings;
    }

    public void saveUser(User user) {
        RegistrationController.saveUser(user);
    }
}
