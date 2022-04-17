package com.example.views.createworkout;

import com.example.data.repository.TagsRepository;
import com.example.data.repository.WorkoutRepository;
import com.example.data.service.TagsService;
import com.example.data.service.WorkoutService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;


/**
 * Front of CreateWorkout class with <b>ERROR_MESSAGE</b> and <b>WorkoutService</b> and <b>TagsService</b> properties.
 * This class creates a tab front createWorkout
 */
@PageTitle("Create Workout")
@Route(value = "create-workout")
@RolesAllowed("USER")
public class CreateWorkoutView extends VerticalLayout {

    /**
     * Constructor - creating a new view for creating workout
     *
     * @param workoutService basic service
     * @param tagsService    basic service
     * @see WorkoutService#WorkoutService(WorkoutRepository)
     * @see TagsService#TagsService(TagsRepository)
     */
    public CreateWorkoutView(@Autowired WorkoutService workoutService, @Autowired TagsService tagsService) {
        CreateWorkoutForm createWorkoutForm = new CreateWorkoutForm(tagsService);
        setHorizontalComponentAlignment(Alignment.CENTER, createWorkoutForm);

        add(createWorkoutForm);
        CreateWorkoutBinder createWorkoutBinder = new CreateWorkoutBinder(createWorkoutForm);
        createWorkoutBinder.addBindingAndValidation(workoutService);
    }

}
