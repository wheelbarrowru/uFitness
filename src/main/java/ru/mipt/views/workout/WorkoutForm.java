package ru.mipt.views.workout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
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
    private WorkoutDTO workoutDTO;
    private final WorkoutService workoutService;
    private final MenuBar ratingBar;
    private final String STAR_SIZE = "33px";
    private final String PALE_STAR_COLOR = "rgb(113, 117, 40)";
    private final Label rating;

    /**
     * Constructor - creating a new view for workouts
     *
     * @param workoutService basic service
     * @param workoutID      workout's id
     * @see WorkoutService#WorkoutService(WorkoutRepository)
     */
    public WorkoutForm(@Autowired WorkoutService workoutService, int workoutID) {
        this.workoutDTO = workoutService.getDTO(workoutID);
        this.workoutService = workoutService;

        Component header = new H2(workoutDTO.getTitle());
        rating = new Label("Rating: " + workoutDTO.getRating());
        rating.addClassNames("text-l", "text-body");

        TextArea text = new TextArea();
        text.setValue(workoutDTO.getDescription());
        text.setReadOnly(true);
        text.setWidth("70%");

        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setLabel("Please rate the workout");
        radioGroup.setItems("1", "2", "3", "4", "5");

        ratingBar = new MenuBar();

        for (int i = 1; i < 6; i++) {
            Icon star = VaadinIcon.STAR.create();
            star.setColor(PALE_STAR_COLOR);
            star.setSize(STAR_SIZE);
            Integer value = i;
            ratingBar.addItem(star, e -> updateAndLock(String.valueOf(value), workoutID));
        }
        ratingBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);


        Div tags = new Div();
        for (TagsDTO tagsDTO : workoutDTO.getWorkoutTags()) {
            Span pending = new Span(tagsDTO.getMessage());
            pending.getElement().getThemeList().add("badge contrast");
            pending.getElement().getStyle().set("margin", "5px");
            tags.add(pending);
        }

        tags.setWidth("70%");
        tags.addClassNames("gap-s", "m-0");

        setSizeFull();

        setHorizontalComponentAlignment(Alignment.CENTER, header);
        setHorizontalComponentAlignment(Alignment.CENTER, rating);
        setHorizontalComponentAlignment(Alignment.CENTER, text);
        setHorizontalComponentAlignment(Alignment.CENTER, ratingBar);
        setHorizontalComponentAlignment(Alignment.CENTER, tags);

        add(header, rating, tags, text, ratingBar);
    }

    /**
     * Update rating stars appearance and rating in system & page
     *
     * @param value     to add
     * @param workoutID workout's id to update
     */
    private void updateAndLock(String value, int workoutID) {
        workoutService.updateRating(workoutDTO, value);
        ratingBar.removeAll();
        for (int i = 1; i < 6; i++) {
            if (i <= Integer.parseInt(value)) {
                Icon star = VaadinIcon.STAR.create();
                star.setColor("yellow");
                star.setSize(STAR_SIZE);
                ratingBar.addItem(star).setEnabled(false);
            } else {
                Icon paleStar = VaadinIcon.STAR.create();
                paleStar.setColor(PALE_STAR_COLOR);
                paleStar.setSize(STAR_SIZE);
                ratingBar.addItem(paleStar).setEnabled(false);
            }
        }
        workoutDTO = workoutService.getDTO(workoutID);
        rating.setText("Rating: " + workoutDTO.getRating());

    }

}
