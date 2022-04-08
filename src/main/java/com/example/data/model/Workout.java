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

    private UUID userid;
    private String description;
    private int raiting = 0;
    private String title;

    @ElementCollection
    @CollectionTable(name = "tags")
    private List<String> workoutTags = new ArrayList<String>();


}