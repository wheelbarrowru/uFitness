package ru.mipt.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Tags entity class with <b>id</b>, <b>message</b> and <b>workouts</b> properties.
 */
@Entity(name = "Tags")
@Table(name = "tags")
@Getter
@Setter
@RequiredArgsConstructor
public class Tags extends AbstractEntity {

    /**
     * one parameter constructor
     *
     * @param message tag's name
     */
    public Tags(String message) {
        this.message = message;
    }

    /**
     * Tags's id
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
     * Tags's name or "message"
     */
    @Column(name = "message")
    private String message;

    /**
     * Workouts with this Tags
     */
    @ManyToMany(mappedBy = "workoutTags", fetch = FetchType.EAGER)
    private Set<Workout> workouts;


    /**
     * This method return is objects equals or not
     *
     * @param o object
     * @return boolean
     * @see Tags#getId()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tags tags = (Tags) o;
        return Objects.equals(getId(), tags.getId());
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

    /**
     * This method returns Tags's parameters in String format
     *
     * @return Workout in String format
     */
    @Override
    public String toString() {
        return "Tags{" +
                "Id=" + Id +
                ", message='" + message + '\'' +
                ", workouts=" + workouts +
                '}';
    }
}
