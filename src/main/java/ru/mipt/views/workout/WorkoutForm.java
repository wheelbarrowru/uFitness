package ru.mipt.views.workout;

import ru.mipt.data.dto.TagsDTO;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.repository.WorkoutRepository;
import ru.mipt.data.service.WorkoutService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Create a FormLayout with all our components. The FormLayout
 * doesn't have any logic (validation, etc.), but it allows us to
 * configure responsiveness from Java code and its defaults looks
 * nicer than just using a Div or a VerticalLayout.
 * <p>
 * This FormLayout itself is added to the MainView, where it is made
 * accessible to the user.
 */
public class WorkoutForm extends VerticalLayout {

    /**
     * Constructor - creating a new view for workouts
     *
     * @param workoutService basic service
     * @param workoutID      workout's id
     * @see WorkoutService#WorkoutService(WorkoutRepository)
     */
    public WorkoutForm(@Autowired WorkoutService workoutService, int workoutID) {
        WorkoutDTO workoutDTO = workoutService.getDTO(workoutID);
        Component header = new H2(workoutDTO.getTitle());
        Label rating = new Label("Rating: " + workoutDTO.getRating());

        TextArea text = new TextArea();
        text.setValue(workoutDTO.getDescription());
        text.setReadOnly(true);
        text.setWidth("70%");

        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setLabel("Please rate the workout");
        radioGroup.setItems("1", "2", "3", "4", "5");
        HorizontalLayout rateButtons = new HorizontalLayout(radioGroup);
        radioGroup.addValueChangeListener(e -> {
            workoutService.updateRating(workoutDTO, radioGroup.getValue());
            radioGroup.setReadOnly(true);
        });

        UnorderedList content = new UnorderedList();
        for (TagsDTO tagsDTO : workoutDTO.getWorkoutTags()) {
            content.add(new ListItem(tagsDTO.getMessage()));
        }

        content.add();

        Details details = new Details("Tags", content);
        details.setOpened(true);
        details.addThemeVariants(DetailsVariant.REVERSE);
        details.setWidth("70%");

        setSizeFull();

        setHorizontalComponentAlignment(Alignment.CENTER, header);
        setHorizontalComponentAlignment(Alignment.CENTER, rating);
        setHorizontalComponentAlignment(Alignment.CENTER, text);
        setHorizontalComponentAlignment(Alignment.CENTER, rateButtons);
        setHorizontalComponentAlignment(Alignment.CENTER, details);

        add(header, rating, details, text, rateButtons);
    }

}
