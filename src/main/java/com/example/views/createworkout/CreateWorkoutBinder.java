package com.example.views.createworkout;

import com.example.data.dto.WorkoutDTO;
import com.example.data.service.WorkoutService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;

public class CreateWorkoutBinder {
    private CreateWorkoutForm createWorkoutForm;

    public CreateWorkoutBinder(CreateWorkoutForm createWorkoutForm) {
        this.createWorkoutForm = createWorkoutForm;
    }
    public void addBindingAndValidation(WorkoutService workoutService){
        BeanValidationBinder<WorkoutDTO> binder = new BeanValidationBinder<>(WorkoutDTO.class);
        binder.bindInstanceFields(createWorkoutForm);
        binder.forField(createWorkoutForm.getTitle())
                .withValidator(this::titleValidation).bind("title");
        binder.forField(createWorkoutForm.getBody())
                .withValidator(this::bodyValidation).bind("description");

        binder.setStatusLabel(createWorkoutForm.getErrorMessageField());

        createWorkoutForm.getButton().addClickListener(event -> {
            try {
                WorkoutDTO workoutDTO = new WorkoutDTO();
                binder.writeBean(workoutDTO);
                workoutDTO.setWorkoutTags(createWorkoutForm.getTagsSet());
                workoutService.update(workoutDTO);

                showSuccess();
            } catch (ValidationException e) {
                //FIXME
                //e.printStackTrace();
                    }
                }
                );
    }
    private ValidationResult titleValidation(String title, ValueContext ctx){
        return !title.isEmpty()? ValidationResult.ok(): ValidationResult.error("Title cannot be empty");
    }
    private ValidationResult bodyValidation(String body, ValueContext ctx){
        return !body.isEmpty()? ValidationResult.ok(): ValidationResult.error("Description cannot be empty");
    }
    private void showSuccess() {
        Notification notification =
                Notification.show("Data saved");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

        UI.getCurrent().navigate("workout-list");
    }
}
