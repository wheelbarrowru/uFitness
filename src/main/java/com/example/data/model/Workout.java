package com.example.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "application_workout")
public class Workout extends AbstractEntity {
    //по аналогии с user. поля + гет/сет
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;
    private int rating = 0;
    private String title;

    @ElementCollection
    @CollectionTable(name = "tags")
    private List<String> workoutTags = new ArrayList<String>();


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void changeRating(int rating) {
        this.rating = (this.rating * 5 + rating) / 5;
    }
}