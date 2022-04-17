package com.example.views.workout;

import com.example.data.repository.WorkoutRepository;
import com.example.data.service.WorkoutService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

/**
 * The main view that holds the workout form
 * <p>
 * This view is itself a component (specifically a VerticalLayout) to
 * which the workout form is added. This view is made accessible
 * to the end-user via the @Route annotation.
 */
@PageTitle("Workout")
@Route(value = "workout")
@RolesAllowed("USER")
public class WorkoutView extends Div implements HasUrlParameter<Integer> {
    private final WorkoutService workoutService;

    /**
     * @param workoutService basic service
     * @see WorkoutService#WorkoutService(WorkoutRepository)
     */
    public WorkoutView(@Autowired WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    /**
     * Method which set id to addWorkoutForm method
     *
     * @param event     event
     * @param parameter workout's id
     */
    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {
        addWorkoutForm(workoutService, parameter);
    }

    /**
     * Method which creates new WorkoutForm
     *
     * @param workoutService basic service
     * @param parameter      workout's id
     * @see WorkoutService#WorkoutService(WorkoutRepository)
     * @see WorkoutForm#WorkoutForm(WorkoutService, int)
     */
    private void addWorkoutForm(@Autowired WorkoutService workoutService, int parameter) {
        WorkoutForm workoutForm = new WorkoutForm(workoutService, parameter);
        add(workoutForm);
    }
}
