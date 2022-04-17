package com.example.views.createworkout;

import com.example.data.repository.TagsRepository;
import com.example.data.repository.WorkoutRepository;
import com.example.data.service.TagsService;
import com.example.data.service.WorkoutService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

/**
 *
 */
@PageTitle("Create Workout")
@Route(value = "create-workout")
@RolesAllowed("USER")
//@AnonymousAllowed
public class CreateWorkoutView extends VerticalLayout {

    /**
     * Constructor - creating a new view for creating workout
     * @param workoutService - workoutService
     * @param tagsService - tagsService
     * @see WorkoutService#WorkoutService(WorkoutRepository)
     * @see TagsService#TagsService(TagsRepository) 
     */
    public CreateWorkoutView(WorkoutService workoutService, TagsService tagsService) {
        CreateWorkoutForm createWorkoutForm = new CreateWorkoutForm(tagsService);
        setHorizontalComponentAlignment(Alignment.CENTER, createWorkoutForm);

        add(createWorkoutForm);
        CreateWorkoutBinder createWorkoutBinder = new CreateWorkoutBinder(createWorkoutForm);
        createWorkoutBinder.addBindingAndValidation(workoutService);
    }

}
