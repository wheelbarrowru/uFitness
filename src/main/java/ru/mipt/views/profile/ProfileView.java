package ru.mipt.views.profile;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.data.repository.UserRepository;
import ru.mipt.data.service.RestClientService;
import ru.mipt.data.service.UserService;
import ru.mipt.security.AuthenticatedUser;

import javax.annotation.security.RolesAllowed;
import java.util.NoSuchElementException;

/**
 * Front of profile class with <b>ERROR_MESSAGE</b> and <b>restClientService</b> and <b>authenticatedUser</b> properties.
 * This class creates a tab front profile
 */
@PageTitle("Profile")
@Route(value = "profile")
@RolesAllowed("USER")
public class ProfileView extends Div implements HasUrlParameter<Integer> {
    private final RestClientService restClientService;
    private final AuthenticatedUser authenticatedUser;
    private final UserService userService;

    /**
     * Method of setting params to addParameter
     *
     * @param beforeEvent Abstract before event class
     *                    that has the common functionalities for  BeforeLeaveEvent and BeforeEnterEvent
     * @param param       id
     */
    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer param) {
        addProfileForm(param);
    }

    /**
     * Method to create ProfileForm
     *
     * @param param user's id
     */
    public void addProfileForm(Integer param) {
        String ERROR_MESSAGE = "You haven't access to this page";
        H2 errorMessage = new H2(ERROR_MESSAGE);
        try {
            if (authenticatedUser.get().orElseThrow().getId() == param) {
                ProfileForm profileForm = new ProfileForm(restClientService, authenticatedUser, userService, param);

                Button back = new Button("back", VaadinIcon.ARROW_LEFT.create());
                back.addClickListener(e -> back.getUI().ifPresent(ui -> ui.navigate("workout-list")));
                back.addClickShortcut(Key.ESCAPE);
                back.addThemeVariants(ButtonVariant.LUMO_LARGE);
                back.addClassName("m-0");

                Icon star = VaadinIcon.STAR.create();
                star.setSize("30px");
                star.setColor("yellow");
                Button favoriteWorkoutsButton = new Button("Favorite workouts", star);
                favoriteWorkoutsButton.addClickListener(e -> favoriteWorkoutsButton.getUI().ifPresent(ui -> ui.navigate("favorite-workouts/" + param)));
                favoriteWorkoutsButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
                favoriteWorkoutsButton.addClassName("m-0");

                HorizontalLayout buttons = new HorizontalLayout(back, favoriteWorkoutsButton);
                buttons.addClassName("m-m");

                add(buttons, profileForm);
            } else {
                add(errorMessage);
            }
        } catch (NoSuchElementException e) {
            add(errorMessage);
        }

    }

    /**
     * Constructor - creating a front of user's profile
     *
     * @param restClientService basic service
     * @param authenticatedUser security class of user authentication
     * @see RestClientService#RestClientService()
     * @see AuthenticatedUser#AuthenticatedUser(UserRepository)
     */
    public ProfileView(@Autowired RestClientService restClientService,
                       @Autowired AuthenticatedUser authenticatedUser,
                       @Autowired UserService userService) {
        this.restClientService = restClientService;
        this.authenticatedUser = authenticatedUser;
        this.userService = userService;
    }

}
