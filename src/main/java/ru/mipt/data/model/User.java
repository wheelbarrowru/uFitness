package ru.mipt.data.model;

import ru.mipt.data.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


/**
 * User entity class with <b>id</b>, <b>firstname</b>, <b>lastname</b>, <b>username</b>,
 * <b>email</b>, <b><roles/b> and <b>password</b> properties.
 */
@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends AbstractEntity {
    /**
     * User's id
     */
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

    /**
     * User's firstname
     */
    @Column(name = "firstname")
    private String firstname;

    /**
     * User's lastname
     */
    @Column(name = "lastname")
    private String lastname;

    /**
     * User's username
     */
    @Column(name = "username")
    private String username;

    /**
     * User's email
     */
    @Column(name = "email")
    private String email;

    /**
     * User's password
     */
    @Column(name = "password")
    private String password;

    /**
     * User's Role
     */
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;


    /*
    @OneToMany(mappedBy = "description", cascade = ALL)
    @ToString.Exclude
    private List<Workout> favoriteTrainings = new ArrayList<Workout>();
    */

    /**
     * This method return is objects equals or not
     *
     * @param o object
     * @return boolean
     * @see User#getId()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    /**
     * Method return hashcode of this class
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
