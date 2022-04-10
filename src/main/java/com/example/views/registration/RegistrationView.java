package com.example.views.registration;

import com.example.data.service.UserService;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

/**
 * The main view that holds the registration form
 * <p>
 * This view is itself a component (specifically a VerticalLayout) to
 * which the registration form is added. This view is made accessible
 * to the end-user via the @Route annotation.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@PageTitle("Registration")
@Route(value = "registration")
@AnonymousAllowed
@Uses(Icon.class)
public class RegistrationView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     */
    public RegistrationView(UserService userService) {
        RegistrationForm registrationForm = new RegistrationForm();

        setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);

        add(registrationForm);

        RegistrationFormBinder registrationFormBinder = new RegistrationFormBinder(registrationForm);
        registrationFormBinder.addBindingAndValidation(userService);

    }
}