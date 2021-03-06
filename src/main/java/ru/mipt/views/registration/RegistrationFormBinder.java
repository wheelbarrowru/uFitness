package ru.mipt.views.registration;

import ru.mipt.data.Role;
import ru.mipt.data.dto.UserDTO;
import ru.mipt.data.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.HashSet;
import java.util.Set;


/**
 * Binding and Validation class for registration with <b>UserService</b>
 */
public class RegistrationFormBinder {

    private final RegistrationForm registrationForm;
    private UserService userService;

    /**
     * Flag for disabling first run for password validation
     */
    private boolean enablePasswordValidation;

    /**
     * @param registrationForm registration form
     */
    public RegistrationFormBinder(RegistrationForm registrationForm) {
        this.registrationForm = registrationForm;
    }

    /**
     * Method to add the data binding and validation logics
     * to the registration form
     */
    public void addBindingAndValidation(@Autowired UserService userService) {
        this.userService = userService;
        BeanValidationBinder<UserDTO> binder = new BeanValidationBinder<>(UserDTO.class);

        binder.bindInstanceFields(registrationForm);

        binder.forField(registrationForm.getPasswordField()).withValidator(this::passwordValidator).bind("password");

        registrationForm.getPasswordConfirmField().addValueChangeListener(e -> {

            enablePasswordValidation = true;

            binder.validate();
        });
        binder.forField(registrationForm.getUsernameField()).withValidator(this::usernameValidator).bind("username");
        binder.forField(registrationForm.getEmailField()).withValidator(this::emailValidator).bind("email");
        binder.forField(registrationForm.getFirstNameField()).withValidator(this::firstNameValidator).bind("firstName");
        binder.forField(registrationForm.getLastName()).withValidator(this::lastNameValidator).bind("lastName");

        binder.setStatusLabel(registrationForm.getErrorMessageField());

        registrationForm.getSubmitButton().addClickListener(event -> {
            try {

                UserDTO userBean = new UserDTO();

                binder.writeBean(userBean);

                userBean.setPassword(BCrypt.hashpw(userBean.getPassword(), BCrypt.gensalt()));
                Set<Role> roles = new HashSet<>();
                roles.add(Role.USER);
                userBean.setRoles(roles);
                userService.update(userBean);

                showSuccess(userBean);

            } catch (ValidationException ignored) {
            }
        });
    }

    /**
     * Method to validate that username is free, less than 30 characters and in English
     *
     * @param username for validate
     */
    private ValidationResult usernameValidator(String username, ValueContext ctx) {
        if (username == null || username.length() > 30) {
            return ValidationResult.error("Username should be less than 30 characters and not null");
        }
        if (!username.matches("^[a-zA-Z0-9]+$")) {
            return ValidationResult.error("Username should be in English");
        }
        return userService.checkNotExistUsername(username) ? ValidationResult.ok() : ValidationResult.error("This username is busy");
    }

    /**
     * This method shows is user's email busy or not
     *
     * @param email user's email
     * @param ctx   context
     * @return ValidationResult
     */
    private ValidationResult emailValidator(String email, ValueContext ctx) {
        if (email.length() >= 40) {
            return ValidationResult.error("Email should be less than 40 characters");
        }
        return userService.checkNotExistEmail(email) ? ValidationResult.ok() : ValidationResult.error("This email is busy");
    }

    /**
     * This method check tht firstname less than 30 characters
     *
     * @param firstName for check
     * @param ctx       context
     * @return ValidationResult
     */
    private ValidationResult firstNameValidator(String firstName, ValueContext ctx) {
        return firstName.length() < 30 ? ValidationResult.ok() : ValidationResult.error("Name should be less than 30 characters");
    }

    /**
     * This method check tht lastname less than 30 characters
     *
     * @param lastName for check
     * @param ctx      context
     * @return ValidationResult
     */
    private ValidationResult lastNameValidator(String lastName, ValueContext ctx) {
        return lastName.length() < 35 ? ValidationResult.ok() : ValidationResult.error("Last name should be less than 35 characters");
    }

    /**
     * Method to validate that:
     * <p>
     * 1) Password is at least 8 characters long
     * <p>
     * 2) Values in both fields match each other
     *
     * @param pass1 first password
     * @param ctx   ValueContext
     * @return is passwords match
     */
    private ValidationResult passwordValidator(String pass1, ValueContext ctx) {


        if (pass1 == null || pass1.length() < 8) {
            return ValidationResult.error("Password should be at least 8 characters long");
        }

        if (!enablePasswordValidation) {

            enablePasswordValidation = true;
            return ValidationResult.ok();
        }

        String pass2 = registrationForm.getPasswordConfirmField().getValue();

        if (pass1.equals(pass2)) {
            return ValidationResult.ok();
        }

        return ValidationResult.error("Passwords do not match");
    }

    /**
     * Method which show is data saved or not <p>Notify user about result and navigate to login page</p>
     */
    private void showSuccess(UserDTO userBean) {
        Notification notification = Notification.show("Data saved, welcome to uFitness "
                + userBean.getFirstName() + "!");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        UI.getCurrent().navigate("login");
    }
}