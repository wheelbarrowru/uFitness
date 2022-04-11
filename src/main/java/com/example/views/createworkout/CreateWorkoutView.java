package com.example.views.createworkout;

import com.example.data.service.TagsService;
import com.example.data.service.WorkoutService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import javax.annotation.security.RolesAllowed;

@PageTitle("Create Workout")
@Route(value = "create-workout")
@RolesAllowed("USER")
//@AnonymousAllowed
public class CreateWorkoutView extends VerticalLayout {

    public CreateWorkoutView(WorkoutService workoutService, TagsService tagsService) {
        CreateWorkoutForm createWorkoutForm = new CreateWorkoutForm(tagsService);
        setHorizontalComponentAlignment(Alignment.CENTER, createWorkoutForm);

        add(createWorkoutForm);
        CreateWorkoutBinder createWorkoutBinder = new CreateWorkoutBinder(createWorkoutForm);
        createWorkoutBinder.addBindingAndValidation(workoutService);
    }

}
