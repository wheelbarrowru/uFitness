package ru.mipt.views.profile;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import ru.mipt.data.dto.UserDTO;
import ru.mipt.data.repository.UserRepository;
import ru.mipt.data.service.RestClientService;
import ru.mipt.data.service.UserService;
import ru.mipt.security.AuthenticatedUser;

import java.util.stream.Stream;


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
    @Getter
    private final TextField usernameField;
    @Getter
    private final TextField firstNameField;
    @Getter
    private final TextField lastNameField;
    @Getter
    private final EmailField emailField;
    @Getter
    private final Span errorMessageField;
    @Getter
    private final Button save;
    @Getter
    private final Button logout;


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

        errorMessageField = new Span();


        usernameField = new TextField("Username");
        usernameField.setValue(userDTO.getUsername());
        usernameField.addClassNames("text-l");

        firstNameField = new TextField("First name");
        firstNameField.setValue(userDTO.getFirstName());
        firstNameField.addClassNames("text-l");

        lastNameField = new TextField("Last name");
        lastNameField.setValue(userDTO.getLastName());
        lastNameField.addClassNames("text-l");

        emailField = new EmailField("Email");
        emailField.setValue(userDTO.getEmail());
        emailField.addClassNames("text-l");

        logout = new Button("Log out");
        logout.setWidthFull();
        logout.addClickListener(e -> authenticatedUser.logout());

        Dialog deleteDialog = new Dialog();
        deleteDialog.setCloseOnEsc(true);
        deleteDialog.add("Are you sure? This action cannot be undone");

        Button delete = new Button("Delete");
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        delete.getStyle().set("margin-right", "auto");
        delete.addClickListener(e -> {
            userService.delete(param);
            authenticatedUser.logout();
        });

        Button cancel = new Button("Cancel", e -> deleteDialog.close());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.addClickShortcut(Key.ESCAPE);

        HorizontalLayout deleteAndCancel = new HorizontalLayout(delete, cancel);
        deleteAndCancel.addClassNames("justify-between", "mt-m");
        deleteDialog.add(deleteAndCancel);

        Button openDelete = new Button("Delete my account", e -> deleteDialog.open());
        openDelete.addClassNames("bg-error", "text-error-contrast");
        openDelete.setWidthFull();

        save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.setIcon(VaadinIcon.CHECK.create());

        setRequiredIndicatorVisible(firstNameField, lastNameField, emailField, usernameField);

        setSizeFull();
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        Section context = new Section(usernameField, firstNameField, lastNameField, emailField);
        context.addClassNames("flex", "flex-col", "mt-s", "mx-s");
        context.setMinWidth("400px");

        HorizontalLayout buttons = new HorizontalLayout(logout, openDelete);
        buttons.addClassNames("justify-between", "self-end", "mt-xl");
        buttons.setMinWidth("400px");

        setHorizontalComponentAlignment(Alignment.CENTER, context);
        setHorizontalComponentAlignment(Alignment.CENTER, save);
        setHorizontalComponentAlignment(Alignment.CENTER, buttons);

        H2 header = new H2("Your profile");
        header.addClassNames("text-3xl", "m-0");
        setHorizontalComponentAlignment(Alignment.CENTER, header);

        add(header, context, save, buttons);
    }

    /**
     * Set indicators for validation
     *
     * @param components components
     */
    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
