package com.example.views.profile;

import com.example.data.repository.UserRepository;
import com.example.data.service.RestClientService;
import com.example.data.service.UserService;
import com.example.security.AuthenticatedUser;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

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
                add(profileForm);
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
