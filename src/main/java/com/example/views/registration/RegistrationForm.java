package com.example.views.registration;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

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
public class RegistrationForm extends FormLayout {

    private static final H2 title = new H2("Signup form");

    private final TextField firstName;
    private final TextField lastName;

    private final EmailField email;

    private final TextField username;

    private final PasswordField password;
    private final PasswordField passwordConfirm;

    private final Checkbox allowMarketing;

    private final Span errorMessageField;

    private final Button submitButton;

    public RegistrationForm() {
        firstName = new TextField("First name");
        lastName = new TextField("Last name");
        email = new EmailField("Email");
        username = new TextField("Username");

        allowMarketing = new Checkbox("Allow Marketing Emails?");
        allowMarketing.getStyle().set("margin-top", "10px");

        password = new PasswordField("Password");
        passwordConfirm = new PasswordField("Confirm password");

        setRequiredIndicatorVisible(firstName, lastName, email, username, password,
                passwordConfirm);

        errorMessageField = new Span();

        submitButton = new Button("Join the community");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(title, firstName, lastName, email, username, password,
                passwordConfirm, allowMarketing, errorMessageField,
                submitButton);

        // Max width of the Form
        setMaxWidth("500px");

        // Allow the form layout to be responsive. On device widths 0-490px
        // we have one column, then we have two. Field labels are always on
        // top of the fields.
        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));


        // These components take full width regardless if we use one column
        // or two (it just looks better that way)
        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(username, 2);
        setColspan(errorMessageField, 2);
        setColspan(submitButton, 2);
    }

    /**
     * @return user's first name
     */
    public TextField getFirstNameField() {
        return firstName;
    }

    /**
     * @return user's last name
     */
    public TextField getLastNameField() {
        return lastName;
    }

    /**
     * @return user's email
     */
    public EmailField getEmailField() {
        return email;
    }

    /**
     * @return user's username
     */
    public TextField getUsernameField() {
        return username;
    }

    /**
     * @return user's password
     */
    public PasswordField getPasswordField() {
        return password;
    }

    /**
     * @return second password to confirm user's password
     */
    public PasswordField getPasswordConfirmField() {
        return passwordConfirm;
    }


    /**
     * @return error message
     */
    public Span getErrorMessageField() {
        return errorMessageField;
    }

    /**
     * @return submit button
     */
    public Button getSubmitButton() {
        return submitButton;
    }

    /**
     * @param components
     */
    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}