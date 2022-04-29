package ru.mipt.views.workout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.data.dto.TagsDTO;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.repository.WorkoutRepository;
import ru.mipt.data.service.WorkoutService;

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
        rating.addClassNames("text-l", "text-body");

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

        Div tags = new Div();
        for (TagsDTO tagsDTO : workoutDTO.getWorkoutTags()) {
            Span pending = new Span(tagsDTO.getMessage());
            pending.getElement().getThemeList().add("badge contrast");
            pending.getElement().getStyle().set("margin","5px");
            tags.add(pending);
        }

        tags.setWidth("70%");
        tags.addClassNames("gap-s", "m-0");

        setSizeFull();

        setHorizontalComponentAlignment(Alignment.CENTER, header);
        setHorizontalComponentAlignment(Alignment.CENTER, rating);
        setHorizontalComponentAlignment(Alignment.CENTER, text);
        setHorizontalComponentAlignment(Alignment.CENTER, rateButtons);
        setHorizontalComponentAlignment(Alignment.CENTER, tags);

        add(header, rating, tags, text, rateButtons);
    }

}
