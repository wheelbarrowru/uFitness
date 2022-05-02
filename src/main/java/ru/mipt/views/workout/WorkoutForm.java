package ru.mipt.views.workout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.data.dto.TagsDTO;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.repository.WorkoutRepository;
import ru.mipt.data.service.UserService;
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
    private final String STAR_COLOR = "#0C6CE9";
    private final String PALE_STAR_COLOR = "#54677F";
    private final Label rating;

    /**
     * Constructor - creating a new view for workouts
     *
     * @param workoutService basic service
     * @param workoutID      workout's id
     * @see WorkoutService#WorkoutService(WorkoutRepository)
     */
    public WorkoutForm(@Autowired WorkoutService workoutService,
                       @Autowired UserService userService,
                       int userId, int workoutID) {
        this.workoutDTO = workoutService.getDTO(workoutID);
        this.workoutService = workoutService;

        Component header = new H2(workoutDTO.getTitle());
        rating = new Label("Rating: " + workoutDTO.getRating());
        rating.addClassNames("text-l", "text-body");

        Button favoriteButton = new Button();
        Icon favoriteStar = VaadinIcon.STAR_O.create();
        favoriteStar.setColor(STAR_COLOR);
        favoriteStar.setSize("38px");
        Icon favoriteStarChosen = VaadinIcon.STAR.create();
        favoriteStarChosen.setColor(STAR_COLOR);
        favoriteStarChosen.setSize("38px");
        favoriteButton.setIcon(favoriteStar);

        if (userService.checkFavorite(workoutID, userId)) {
            favoriteButton.setIcon(favoriteStarChosen);
        } else {
            favoriteButton.setIcon(favoriteStar);
        }
        favoriteButton.addClickListener(e -> {
            if (favoriteButton.getIcon().equals(favoriteStar)) {
                favoriteButton.setIcon(favoriteStarChosen);
                userService.addFavoriteWorkout(userId, workoutDTO.getId());
            } else {
                favoriteButton.setIcon(favoriteStar);
                userService.removeFavoriteWorkouts(userId, workoutDTO.getId());
            }
        });
        favoriteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        favoriteButton.setMaxHeight("40px");

        HorizontalLayout headerAndFavoriteButton = new HorizontalLayout(header, favoriteButton);
        headerAndFavoriteButton.addClassNames("items-center");

        TextArea text = new TextArea();
        text.setValue(workoutDTO.getDescription());
        text.setReadOnly(true);
        text.setWidth("70%");


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

        setHorizontalComponentAlignment(Alignment.CENTER, headerAndFavoriteButton);
        setHorizontalComponentAlignment(Alignment.CENTER, rating);
        setHorizontalComponentAlignment(Alignment.CENTER, text);
        setHorizontalComponentAlignment(Alignment.CENTER, tags);

        add(headerAndFavoriteButton, rating, tags, text);

        ratingBar = new MenuBar();

        for (int i = 1; i < 6; i++) {
            Icon star = VaadinIcon.STAR.create();
            star.setColor(PALE_STAR_COLOR);
            star.setSize(STAR_SIZE);
            Integer value = i;
            ratingBar.addItem(star, e -> updateAndLock(String.valueOf(value), workoutID));
        }
        ratingBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);
        setHorizontalComponentAlignment(Alignment.CENTER, ratingBar);

        if (userId == workoutDTO.getAuthorId()) {
            Button delete = new Button("Delete my workout");
            delete.addClassNames("bg-error", "text-error-contrast");
            setHorizontalComponentAlignment(Alignment.CENTER, delete);
            add(delete);
            delete.addClickListener(e -> {
                workoutService.delete(workoutDTO.getId());
                UI.getCurrent().getPage().getHistory().back();
            });
        } else {
            add(ratingBar);
        }


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
                star.setColor(STAR_COLOR);

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
        if (workoutDTO == null) {
            UI.getCurrent().getPage().getHistory().back();
        } else {
            rating.setText("Rating: " + workoutDTO.getRating());
        }


    }

}
