package com.example.data.model;

import com.example.data.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.*;


@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends AbstractEntity {
    @JsonProperty(value = "id")
    @Id
    @SequenceGenerator(
            name = "id",
            sequenceName = "id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id"
    )
    @Column(name = "id", insertable = false, updatable = false)
    @ApiModelProperty(notes = "The database generated user's ID")
    private int Id;

    @Column(name = "firstname")
    @ApiModelProperty(notes = "User's firstname", required = true)
    private String firstname;
    @Column(name = "lastname")
    @ApiModelProperty(notes = "User's lastname", required = true)
    private String lastname;
    @Column(name = "username")
    @ApiModelProperty(notes = "User's username", required = true)
    private String username;
    @Column(name = "email")
    @ApiModelProperty(notes = "User's email", required = true)
    private String email;

    @Column(name = "password")
    @ApiModelProperty(notes = "User's password", required = true)
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "User's Role", required = true)
    private Set<Role> roles;
    /*
    @OneToMany(mappedBy = "description", cascade = ALL)
    @ToString.Exclude
    private List<Workout> favoriteTrainings = new ArrayList<Workout>();
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
