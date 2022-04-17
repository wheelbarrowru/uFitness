package com.example.views.profile;

import com.example.data.dto.UserDTO;
import com.example.data.repository.UserRepository;
import com.example.data.service.RestClientService;
import com.example.data.service.UserService;
import com.example.security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


/**
 * Create a FormLayout with all our components. The FormLayout
 * doesn't have any logic (validation, etc.), but it allows us to
 * configure responsiveness from Java code and its defaults looks
 * nicer than just using a Div or a VerticalLayout.
 * <p>
 * This FormLayout itself is added to the MainView, where it is made
 * accessible to the user.
 */
public class ProfileForm extends VerticalLayout {

    /**
     * Constructor - creating a new form of profile
     *
     * @param restClientService basic service
     * @param authenticatedUser security class of user authentication
     * @param param             user's id
     * @see RestClientService#RestClientService()
     * @see AuthenticatedUser#AuthenticatedUser(UserRepository)
     */
    public ProfileForm(RestClientService restClientService, AuthenticatedUser authenticatedUser,
                       UserService userService, Integer param) {
        UserDTO userDTO = restClientService.fetchUserProfile(param);

        Label usernameLabel = new Label("Username: ");
        usernameLabel.addClassNames("text-l");
        Label firstNameLabel = new Label("First name: ");
        firstNameLabel.addClassNames("text-l");
        Label lastNameLabel = new Label("Second name: ");
        lastNameLabel.addClassNames("text-l");
        Label emailLabel = new Label("Email: ");
        emailLabel.addClassNames("text-l");

        Label username = new Label(userDTO.getUsername());
        username.addClassNames("text-l");
        Label firstName = new Label(userDTO.getFirstName());
        firstName.addClassNames("text-l");
        Label lastName = new Label(userDTO.getLastName());
        lastName.addClassNames("text-l");
        Label email = new Label(userDTO.getEmail());
        email.addClassNames("text-l");
        Button logout = new Button("Log out");
        Button delete = new Button("Delete my account");
        delete.addClassNames("bg-error", "text-error-contrast");
        logout.setWidthFull();
        delete.setWidthFull();

        logout.addClickListener(e -> authenticatedUser.logout());

        delete.addClickListener(e -> {
            userService.delete(param);
            authenticatedUser.logout();
        });

        setSizeFull();
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        VerticalLayout context = new VerticalLayout();
        context.add(new HorizontalLayout(usernameLabel, username));
        context.add(new HorizontalLayout(firstNameLabel, firstName));
        context.add(new HorizontalLayout(lastNameLabel, lastName));
        context.add(new HorizontalLayout(emailLabel, email));
        context.setWidth("450px");

        HorizontalLayout buttons = new HorizontalLayout(logout, delete);
        buttons.addClassNames("justify-between", "self-end");
        buttons.setWidthFull();

        HorizontalLayout space = new HorizontalLayout();
        context.add(space);

        setHorizontalComponentAlignment(Alignment.CENTER, context);
        setHorizontalComponentAlignment(Alignment.CENTER, buttons);
        H2 header = new H2("Your profile");
        setHorizontalComponentAlignment(Alignment.CENTER, header);
        context.add(buttons);
        add(header, context);
    }
}
