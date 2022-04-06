package com.example.data.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "application_workout")
public class Workout extends AbstractEntity {
    //по аналогии с user. поля + гет/сет

    private String description;

}