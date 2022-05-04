package ru.mipt.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "WorkoutVotedUser")
@Table(name = "workouts_voted_users")
@Data
public class WorkoutVotedUser extends AbstractEntity{
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

    @Column(name = "workout_id")
    private int workoutId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_vote")
    private int userVote;
}
