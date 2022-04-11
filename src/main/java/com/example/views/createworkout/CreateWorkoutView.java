package com.example.views.createworkout;

import com.example.data.service.TagsService;
import com.example.data.service.WorkoutService;
import com.example.views.tagsgrid.TagsGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Create Workout")
@Route(value = "create-workout")
//@PermitAll
@AnonymousAllowed
public class CreateWorkoutView extends VerticalLayout {

    public CreateWorkoutView(WorkoutService workoutService, TagsService tagsService) {
        CreateWorkoutForm createWorkoutForm = new CreateWorkoutForm(tagsService);
        setHorizontalComponentAlignment(Alignment.CENTER, createWorkoutForm);

        add(createWorkoutForm);
        CreateWorkoutBinder createWorkoutBinder = new CreateWorkoutBinder(createWorkoutForm);
        createWorkoutBinder.addBindingAndValidation(workoutService);
    }

}
