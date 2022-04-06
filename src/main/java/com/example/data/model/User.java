package com.example.data.model;

import com.example.data.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstname;
    private String lastname;
    private String username;
    private String email;

    @JsonIgnore
    private String hashedPassword;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @OneToMany(mappedBy = "description", cascade = ALL)
    private List<Workout> favoriteTrainings = new ArrayList<Workout>();
    @Lob
    //private String profilePictureUrl;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getMail() {
        return email;
    }

    public void setMail(String name) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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
}
