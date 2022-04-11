package com.example.views.workout;

import com.example.data.service.WorkoutService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Workout")
@Route(value = "workout")
//@PermitAll
@AnonymousAllowed
public class WorkoutView extends Div implements HasUrlParameter<Integer> {
    private WorkoutService workoutService;


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
