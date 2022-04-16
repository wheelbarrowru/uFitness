package com.example.views.workout;

import com.example.data.service.WorkoutService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

@PageTitle("Workout")
@Route(value = "workout")
@RolesAllowed("USER")
//@AnonymousAllowed
public class WorkoutView extends Div implements HasUrlParameter<Integer> {
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutView(WorkoutService workoutService) {
        this.workoutService=workoutService;
    }

    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {
        addWorkoutForm(workoutService,parameter);
    }

    private void addWorkoutForm(WorkoutService workoutService, int parameter){
        WorkoutForm workoutForm = new WorkoutForm(workoutService, parameter);
        add(workoutForm);
    }
}
