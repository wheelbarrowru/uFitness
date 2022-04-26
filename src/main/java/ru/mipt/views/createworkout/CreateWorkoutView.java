package ru.mipt.views.createworkout;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import ru.mipt.data.repository.TagsRepository;
import ru.mipt.data.repository.WorkoutRepository;
import ru.mipt.data.service.TagsService;
import ru.mipt.data.service.WorkoutService;
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

        Button back = new Button("back", VaadinIcon.ARROW_LEFT.create());
        back.addClickListener(e -> back.getUI().ifPresent(ui -> ui.navigate("workout-list")));
        setHorizontalComponentAlignment(Alignment.START, back);
        back.addClickShortcut(Key.ESCAPE);
        back.addThemeVariants(ButtonVariant.LUMO_LARGE);

        addClassName("m-0");
        back.addClassName("m-0");
        createWorkoutForm.addClassName("m-0");

        add(back, createWorkoutForm);
        CreateWorkoutBinder createWorkoutBinder = new CreateWorkoutBinder(createWorkoutForm);
        createWorkoutBinder.addBindingAndValidation(workoutService);
    }

}
