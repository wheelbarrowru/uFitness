package com.example.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Workout")
@Table(name = "workouts")
@Getter
@Setter
@RequiredArgsConstructor
public class Workout extends AbstractEntity {
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

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private double rating;

    @Column(name = "count")
    private int countVote;

    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "workouts_tags",
            joinColumns = @JoinColumn(name = "workouts_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private Set<Tags> workoutTags;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Workout workout = (Workout) o;
        return Objects.equals(getId(), workout.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

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