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
 * Workout entity class with <b>id</b>, <b>title</b>, <b>description</b>, <b>rating</b>,
 * <b>countVote</b> and <b>workoutTags</b> properties.
 */
@Entity(name = "Workout")
@Table(name = "workouts")
@Getter
@Setter
@RequiredArgsConstructor
public class Workout extends AbstractEntity {
    /**
     * Workout's generated in database id
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
    private int id;

    /**
     * "Name" of Workout
     */
    @Column(name = "title")
    private String title;

    /**
     * Description of exercises in workout
     */
    @Column(name = "description")
    private String description;

    /**
     * User's rating of workout
     */
    @Column(name = "rating")
    private double rating;

    /**
     * Amount of user's votes
     */
    @Column(name = "count")
    private int countVote;

    /**
     * Set of tags of this workout
     */
    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "workouts_tags",
            joinColumns = @JoinColumn(name = "workouts_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private Set<Tags> workoutTags;


    /**
     * This method return is objects equals or not
     * @param o object
     * @return boolean
     * @see Workout#getId()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Workout workout = (Workout) o;
        return Objects.equals(getId(), workout.getId());
    }

    /**
     * Method return hashcode of this class
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /**
     * This method returns workout's parameters in String format
     * @return Workout in String format
     */
    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", workoutTags=" + workoutTags +
                '}';
    }
}