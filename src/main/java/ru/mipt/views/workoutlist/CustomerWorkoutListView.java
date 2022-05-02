package ru.mipt.views.workoutlist;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.data.service.FindWorkoutsService;
import ru.mipt.data.service.WorkoutService;
import ru.mipt.security.AuthenticatedUser;

import javax.annotation.security.RolesAllowed;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
//FIXME

/**
 * The view that holds the favorite-workouts-page form
 * <p>
 * This view is itself a component (specifically a VerticalLayout) to
 * which the start-page form is added. This view is made accessible
 * to the end-user via the @Route annotation.
 * <p>
 * A new instance of this class opens every time you starts web application
 * browser tab/window.
 */
@PageTitle("Your workouts")
@Route(value = "customer-workouts")
@RolesAllowed("USER")
public class CustomerWorkoutListView extends Div implements HasComponents, HasStyle, HasUrlParameter<Integer> {

    private final AuthenticatedUser authenticatedUser;
    private final FindWorkoutsService findWorkoutsService;
    private final WorkoutService workoutService;

    /**
     * Method of setting params to addParameter
     *
     * @param beforeEvent Abstract before event class
     *                    that has the common functionalities for  BeforeLeaveEvent and BeforeEnterEvent
     * @param param       id
     */
    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer param) {
        addCustomerWorkoutForm(param);
    }

    public CustomerWorkoutListView(@Autowired AuthenticatedUser authenticatedUser,
                                   @Autowired FindWorkoutsService findWorkoutsService,
                                   @Autowired WorkoutService workoutService) {
        this.authenticatedUser = authenticatedUser;
        this.findWorkoutsService = findWorkoutsService;
        this.workoutService = workoutService;
    }

    public void addCustomerWorkoutForm(Integer param) {
        try {
            if (authenticatedUser.get().orElseThrow().getId() == param) {
                BasicWorkoutListForm basicWorkoutListForm = new BasicWorkoutListForm(workoutService,
                        findWorkoutsService.findWorkoutByAuthorId(param)
                                .stream().sorted().collect(Collectors.toList()));
                basicWorkoutListForm.getHeader().setText("Your workouts");

                Button back = new Button("back", VaadinIcon.ARROW_LEFT.create());
                back.addClickListener(e -> back.getUI().ifPresent(ui -> ui.getPage().getHistory().back()));
                back.addClickShortcut(Key.ESCAPE);
                back.addThemeVariants(ButtonVariant.LUMO_LARGE);
                back.addClassName("m-m");

                add(back, basicWorkoutListForm);
            } else {
                UI.getCurrent().getPage().getHistory().back();
            }
        } catch (NoSuchElementException e) {
            UI.getCurrent().getPage().getHistory().back();
        }

    }

}
