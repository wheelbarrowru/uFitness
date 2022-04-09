package com.example.views.registration;

import com.example.data.Role;
import com.example.data.controller.UserController;
import com.example.data.dto.UserDTO;
import com.example.data.model.User;
import com.example.data.repository.UserRepository;
import com.example.data.service.UserService;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegistrationFormBinder {

    private RegistrationForm registrationForm;


    /**
     * Flag for disabling first run for password validation
     */
    private boolean enablePasswordValidation;

    public RegistrationFormBinder(RegistrationForm registrationForm) {
        this.registrationForm = registrationForm;
    }

    /**
     * Method to add the data binding and validation logics
     * to the registration form
     */
    public void addBindingAndValidation() {
        BeanValidationBinder<UserDTO> binder = new BeanValidationBinder<>(UserDTO.class);

        // The bindInstanceFields method facilitates automatic data binding and validation:
        // it automatically matches the fields of the RegistrationForm object to the
        // properties of the UserDetails object based on their names.
        binder.bindInstanceFields(registrationForm);

        // A custom validator for password fields to handle password-confirmation logic
//        binder.forField(registrationForm.getPasswordField())
//                .withValidator(this::passwordValidator).bind("password");
//        binder.forField(registrationForm.getUsernameField())
//                .withValidator(this::usernameValidator).bind("username");
//        binder.forField(registrationForm.getEmailField())
//                .withValidator(this::emailValidator).bind("email");
        // The second password field is not connected to the Binder, but we
        // want the binder to re-check the password validator when the field
        // value changes. The easiest way is just to do that manually.
        registrationForm.getPasswordConfirmField().addValueChangeListener(e -> {
            // The user has modified the second field, now we can validate and show errors.
            // See passwordValidator() for how this flag is used.
            enablePasswordValidation = true;

            binder.validate();
        });

        // Set the label where bean-level error messages go
        binder.setStatusLabel(registrationForm.getErrorMessageField());

        // And finally the submit button
        registrationForm.getSubmitButton().addClickListener(event -> {
            try {

                // Create empty bean to store the details into

                UserDTO userBean = new UserDTO();
                binder.writeBean(userBean);
                User user = new User();
                user = userBean.fromWithoutRoles();
                System.out.println(userBean);
                user.setFirstname(registrationForm.getFirstNameField().getValue());
                user.setLastname(registrationForm.getLastNameField().getValue());
                user.setUsername(registrationForm.getUsernameField().getValue());
                user.setHashedPassword(registrationForm.getPasswordField().getValue());
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println(registrationForm.getEmailField().getValue());
                Set<Role> roles = Stream.of(Role.USER)
                        .collect(Collectors.toCollection(HashSet::new));
                user.setRoles(roles);
                user.setEmail(userBean.getEmail());
                user.setId(UUID.randomUUID());
                user.saveUser(user);
                // Run validators and write the values to the bean


                // Typically, you would here call backend to store the bean

                // Show success message if everything went well
                showSuccess(userBean);

            } catch (ValidationException exception) {
                // validation errors are already visible for each field,
                // and bean-level errors are shown in the status label.

                // We could show additional messages here if we want, do logging, etc.

            }
        });
    }

    /**
     * Method to validate that:
     * <p>
     * 1) Password is at least 8 characters long
     * <p>
     * 2) Values in both fields match each other
     */
    private ValidationResult usernameValidator(String username, ValueContext ctx) {
        return ValidationResult.ok();
    }

    private ValidationResult emailValidator(String email, ValueContext ctx) {
        return ValidationResult.ok();
    }

    private ValidationResult passwordValidator(String pass1, ValueContext ctx) {

        /*
         * Just a simple length check. A real version should check for password
         * complexity as well!
         */
        if (pass1 == null || pass1.length() < 8) {
            return ValidationResult.error("Password should be at least 8 characters long");
        }

        if (!enablePasswordValidation) {
            // user hasn't visited the field yet, so don't validate just yet, but next time.
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }

        String pass2 = registrationForm.getPasswordConfirmField().getValue();

        if (pass1 != null && pass1.equals(pass2)) {
            return ValidationResult.ok();
        }

        return ValidationResult.error("Passwords do not match");
    }

    /**
     * We call this method when form submission has succeeded
     */
    private void showSuccess(UserDTO userBean) {
        Notification notification =
                Notification.show("Data saved, welcome " + userBean.getFirstName());
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        // Here you'd typically redirect the user to another view
    }
}