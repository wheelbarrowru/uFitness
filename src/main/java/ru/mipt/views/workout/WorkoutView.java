package ru.mipt.views.workout;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.data.model.User;
import ru.mipt.data.repository.WorkoutRepository;
import ru.mipt.data.service.UserService;
import ru.mipt.data.service.WorkoutService;
import ru.mipt.security.AuthenticatedUser;

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
    private final UserService userService;
    private final User user;

    /**
     * @param workoutService    basic service
     * @param authenticatedUser basic service
     * @see WorkoutService#WorkoutService(WorkoutRepository)
     */
    public WorkoutView(@Autowired WorkoutService workoutService,
                       @Autowired AuthenticatedUser authenticatedUser,
                       @Autowired UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
        user = authenticatedUser.get().orElse(new User());
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
     */
    private void addWorkoutForm(@Autowired WorkoutService workoutService, int parameter) {
        WorkoutForm workoutForm = new WorkoutForm(workoutService, userService, user.getId(), parameter);
        addClassName("m-s");
        Button back = new Button("back", VaadinIcon.ARROW_LEFT.create());
        back.addClickListener(e -> back.getUI().ifPresent(ui -> ui.getPage().getHistory().back()));
        back.addClickShortcut(Key.ESCAPE);
        back.addThemeVariants(ButtonVariant.LUMO_LARGE);
        back.addClassName("m-s");

        add(back, workoutForm);
    }
}
