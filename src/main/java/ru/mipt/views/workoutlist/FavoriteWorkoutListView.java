package ru.mipt.views.workoutlist;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.data.service.UserService;
import ru.mipt.data.service.WorkoutService;
import ru.mipt.security.AuthenticatedUser;

import javax.annotation.security.RolesAllowed;
import java.util.NoSuchElementException;


@PageTitle("Favorite workouts")
@Route(value = "favorite-workouts")
@RolesAllowed("USER")
public class FavoriteWorkoutListView extends Div implements HasComponents, HasStyle, HasUrlParameter<Integer> {

    private final AuthenticatedUser authenticatedUser;
    private final UserService userService;
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
        addFavoriteWorkoutForm(param);
    }

    public FavoriteWorkoutListView(@Autowired AuthenticatedUser authenticatedUser,
                                   @Autowired UserService userService,
                                   @Autowired WorkoutService workoutService) {
        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
        this.workoutService = workoutService;
    }

    public void addFavoriteWorkoutForm(Integer param) {
        String ERROR_MESSAGE = "You haven't access to this page";
        H2 errorMessage = new H2(ERROR_MESSAGE);
        try {
            if (authenticatedUser.get().orElseThrow().getId() == param) {
                FavoriteWorkoutListForm favoriteWorkoutListForm = new FavoriteWorkoutListForm(userService, workoutService, param);

                Button back = new Button("back", VaadinIcon.ARROW_LEFT.create());
                back.addClickListener(e -> back.getUI().ifPresent(ui -> ui.getPage().getHistory().back()));
                back.addClickShortcut(Key.ESCAPE);
                back.addThemeVariants(ButtonVariant.LUMO_LARGE);
                back.addClassName("m-m");

                add(back, favoriteWorkoutListForm);
            } else {
                add(errorMessage);
            }
        } catch (NoSuchElementException e) {
            add(errorMessage);
        }

    }

}
