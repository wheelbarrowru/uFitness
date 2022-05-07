package ru.mipt.views.createworkout;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.model.User;
import ru.mipt.data.repository.WorkoutRepository;
import ru.mipt.data.service.TagsService;
import ru.mipt.data.service.WorkoutService;
import ru.mipt.security.AuthenticatedUser;

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
    public void addBindingAndValidation(@Autowired WorkoutService workoutService,
                                        @Autowired AuthenticatedUser authenticatedUser) {
        BeanValidationBinder<WorkoutDTO> binder = new BeanValidationBinder<>(WorkoutDTO.class);
        binder.bindInstanceFields(createWorkoutForm);
        binder.forField(createWorkoutForm.getTitle()).withValidator(this::titleValidation).bind("title");
        binder.forField(createWorkoutForm.getBody()).withValidator(this::bodyValidation).bind("description");

        binder.setStatusLabel(createWorkoutForm.getErrorMessageField());

        createWorkoutForm.getButton().addClickListener(event -> {
            try {
                WorkoutDTO workoutDTO = new WorkoutDTO();
                binder.writeBean(workoutDTO);
                if (createWorkoutForm.getTagsSet().isEmpty()) {
                    throw new Exception();
                }
                workoutDTO.setWorkoutTags(createWorkoutForm.getTagsSet());
                workoutDTO.setAuthorId(authenticatedUser.get().orElse(new User()).getId());
                workoutService.update(workoutDTO);

                showSuccess();
            } catch (ValidationException ignored) {
            } catch (Exception e) {
                showFail();
            }
        });
    }

    /**
     * @param title workout's title
     * @param ctx   context
     * @return ValidationResult
     */
    private ValidationResult titleValidation(String title, ValueContext ctx) {
        if (title.length() > 100) return ValidationResult.error("Title should be less than 100 characters");

        return !title.isEmpty() ? ValidationResult.ok() : ValidationResult.error("Title cannot be empty");
    }

    /**
     * @param body workout's description body
     * @param ctx  context
     * @return ValidationResult
     */
    private ValidationResult bodyValidation(String body, ValueContext ctx) {
        if (body.length() > 1000) return ValidationResult.error("Description should be less than 1000 characters");

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

    /**
     * This method throws exeption if you're trying to create workout without tags
     */
    private void showFail() {
        Notification notification = Notification.show("Choose at least one tag");
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

    }
}
