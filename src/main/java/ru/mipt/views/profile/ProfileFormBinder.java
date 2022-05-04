package ru.mipt.views.profile;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.data.dto.UserDTO;
import ru.mipt.data.service.UserService;

/**
 * Binding and Validation class for updating user info with <b>UserService</b>
 */
public class ProfileFormBinder {

    private final ProfileForm profileForm;
    private UserService userService;
    private final int userId;

    /**
     * @param profileForm registration form
     */
    public ProfileFormBinder(ProfileForm profileForm, int userId) {
        this.profileForm = profileForm;
        this.userId = userId;
    }

    /**
     * Method to add the data binding and validation logics
     * to the registration form
     */
    public void addBindingAndValidation(@Autowired UserService userService) {
        this.userService = userService;

        BeanValidationBinder<UserDTO> binder = new BeanValidationBinder<>(UserDTO.class);

        binder.forField(profileForm.getUsernameField()).withValidator(this::usernameValidator).bind("username");
        binder.forField(profileForm.getEmailField()).withValidator(this::emailValidator).bind("email");
        binder.forField(profileForm.getFirstNameField()).withValidator(this::firstNameValidator).bind("firstName");
        binder.forField(profileForm.getLastNameField()).withValidator(this::lastNameValidator).bind("lastName");

        binder.setStatusLabel(profileForm.getErrorMessageField());

        profileForm.getSave().addClickListener(event -> {
            try {

                UserDTO userBean = new UserDTO();

                binder.writeBean(userBean);

                userService.updateUserInfo(userId, userBean.getUsername(), userBean.getFirstName(),
                        userBean.getLastName(), userBean.getEmail());

                showSuccess();

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
        return userService.checkNotExistUsernameWithDifferentID(username, userId) ? ValidationResult.ok() : ValidationResult.error("This username is busy");
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
        return userService.checkNotExistEmailWithDifferentID(email, userId) ? ValidationResult.ok() : ValidationResult.error("This email is busy");
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
     * Method which show is data saved or not <p>Notify user about result and navigate to login page</p>
     */
    private void showSuccess() {
        Notification notification = Notification.show("Data saved!");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }
}