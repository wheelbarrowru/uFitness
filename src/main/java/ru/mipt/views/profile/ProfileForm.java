package ru.mipt.views.profile;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import ru.mipt.data.dto.UserDTO;
import ru.mipt.data.repository.UserRepository;
import ru.mipt.data.service.RestClientService;
import ru.mipt.data.service.UserService;
import ru.mipt.security.AuthenticatedUser;


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

        TextField username = new TextField("Username");
        username.setValue(userDTO.getUsername());
        username.addClassNames("text-l");
        username.setReadOnly(true);

        TextField firstName = new TextField("First name");
        firstName.setValue(userDTO.getFirstName());
        firstName.addClassNames("text-l");
        firstName.setReadOnly(true);

        TextField lastName = new TextField("Last name");
        lastName.setValue(userDTO.getLastName());
        lastName.addClassNames("text-l");
        lastName.setReadOnly(true);

        TextField email = new TextField("Email");
        email.setValue(userDTO.getEmail());
        email.addClassNames("text-l");
        email.setReadOnly(true);

        Button logout = new Button("Log out");
        logout.setWidthFull();
        logout.addClickListener(e -> authenticatedUser.logout());

        Button delete = new Button("Delete my account");
        delete.addClassNames("bg-error", "text-error-contrast");
        delete.setWidthFull();
        delete.addClickListener(e -> {
            userService.delete(param);
            authenticatedUser.logout();
        });

        setSizeFull();
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        Section context = new Section(username, firstName, lastName, email);
        context.addClassNames("flex", "flex-col", "mb-xl", "mt-m", "mx-s");
        context.setMinWidth("400px");

        HorizontalLayout buttons = new HorizontalLayout(logout, delete);
        buttons.addClassNames("justify-between", "self-end", "mt-xl");
        buttons.setMinWidth("400px");

        setHorizontalComponentAlignment(Alignment.CENTER, context);
        setHorizontalComponentAlignment(Alignment.CENTER, buttons);

        H2 header = new H2("Your profile");
        setHorizontalComponentAlignment(Alignment.CENTER, header);

        add(header, context, buttons);
    }
}
