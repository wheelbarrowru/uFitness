package com.example.views.createworkout;

import com.example.data.dto.WorkoutDTO;
import com.example.data.repository.WorkoutRepository;
import com.example.data.service.TagsService;
import com.example.data.service.WorkoutService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Binding and Validation class for creating workout with <b>WorkoutService</b>
 */
public class CreateWorkoutBinder {
    /**
     * field of class create workout form
     */
    private final CreateWorkoutForm createWorkoutForm;

    /**
     * Constructor - creating a new object
     *
     * @param createWorkoutForm object of creatingWorkoutForm
     * @see CreateWorkoutForm#CreateWorkoutForm(TagsService)
     */
    public CreateWorkoutBinder(CreateWorkoutForm createWorkoutForm) {
        this.createWorkoutForm = createWorkoutForm;
    }

    /**
     * Method to add Binding And Validation
     *
     * @param workoutService workoutService
     * @see WorkoutService#WorkoutService(WorkoutRepository)
     */
    public void addBindingAndValidation(@Autowired WorkoutService workoutService) {
        BeanValidationBinder<WorkoutDTO> binder = new BeanValidationBinder<>(WorkoutDTO.class);
        binder.bindInstanceFields(createWorkoutForm);
        binder.forField(createWorkoutForm.getTitle()).withValidator(this::titleValidation).bind("title");
        binder.forField(createWorkoutForm.getBody()).withValidator(this::bodyValidation).bind("description");

        binder.setStatusLabel(createWorkoutForm.getErrorMessageField());

        createWorkoutForm.getButton().addClickListener(event -> {
            try {
                WorkoutDTO workoutDTO = new WorkoutDTO();
                binder.writeBean(workoutDTO);
                workoutDTO.setWorkoutTags(createWorkoutForm.getTagsSet());
                workoutService.update(workoutDTO);

                showSuccess();
            } catch (ValidationException ignored) {
            }
        });
    }

    /**
     * @param title workout's title
     * @param ctx   context
     * @return ValidationResult
     */
    private ValidationResult titleValidation(String title, ValueContext ctx) {
        return !title.isEmpty() ? ValidationResult.ok() : ValidationResult.error("Title cannot be empty");
    }

    /**
     * @param body workout's description body
     * @param ctx  context
     * @return ValidationResult
     */
    private ValidationResult bodyValidation(String body, ValueContext ctx) {
        return !body.isEmpty() ? ValidationResult.ok() : ValidationResult.error("Description cannot be empty");
    }

    /**
     * Method which show is data saved or not <p>Notify user about result and navigate to workout-list page</p>
     */
    private void showSuccess() {
        Notification notification = Notification.show("Your new workout saved!");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        UI.getCurrent().navigate("workout-list");
    }
}
