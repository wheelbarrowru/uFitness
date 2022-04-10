package com.example.data.model;

import com.example.data.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private int Id;

    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
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
