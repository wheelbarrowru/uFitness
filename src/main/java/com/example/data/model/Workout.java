package com.example.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Workout")
@Table(name = "workouts")
@Getter
@Setter
@ToString
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

    @Column(name = "title",insertable = false, updatable = false)
    private String title;

    @Column(name = "description",insertable = false, updatable = false)
    private String description;

    @Column(name = "rating",insertable = false, updatable = false)
    private int rating;


    @ElementCollection
    @CollectionTable(name = "tags")
    private List<String> workoutTags = new ArrayList<String>();


    public void changeRating(int rating) {
        this.rating = (this.rating * 5 + rating) / 5;
    }

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
}