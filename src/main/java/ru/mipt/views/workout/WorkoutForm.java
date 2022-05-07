package ru.mipt.views.workout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
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
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
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
    private final String STAR_COLOR = "#0C6CE9";
    private final Label rating;

    /**
     * Constructor - creating a new view for workouts
     *
     * @param workoutService basic service
     * @param workoutId      workout's id
     * @see WorkoutService#WorkoutService(WorkoutRepository)
     */
    public WorkoutForm(@Autowired WorkoutService workoutService,
                       @Autowired UserService userService,
                       int userId, int workoutId) {
        this.workoutDTO = workoutService.getDTO(workoutId);
        this.workoutService = workoutService;

        String size;
        if (isMobileDevice()) {
            size = "90%";
        } else {
            size = "70%";
        }

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

        if (userService.checkFavorite(workoutId, userId)) {
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
        text.setWidth(size);


        Div tags = new Div();
        for (TagsDTO tagsDTO : workoutDTO.getWorkoutTags()) {
            Span pending = new Span(tagsDTO.getMessage());
            pending.getElement().getThemeList().add("badge contrast");
            pending.getElement().getStyle().set("margin", "5px");
            tags.add(pending);
        }
        tags.setWidth(size);
        tags.addClassNames("gap-s", "m-0");


        setSizeFull();

        setHorizontalComponentAlignment(Alignment.CENTER, headerAndFavoriteButton);
        setHorizontalComponentAlignment(Alignment.CENTER, rating);
        setHorizontalComponentAlignment(Alignment.CENTER, text);
        setHorizontalComponentAlignment(Alignment.CENTER, tags);

        add(headerAndFavoriteButton, rating, tags, text);

        ratingBar = new MenuBar();
        initAndUpdateRatingButtons("0", userId, workoutId);

        ratingBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);
        setHorizontalComponentAlignment(Alignment.CENTER, ratingBar);

        if (userId == workoutDTO.getAuthorId()) {

            Dialog deleteDialog = new Dialog();
            deleteDialog.setCloseOnEsc(true);
            deleteDialog.add("Are you sure? This action cannot be undone");

            Button delete = new Button("Delete");
            delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            delete.getStyle().set("margin-right", "auto");
            delete.addClickListener(e -> {
                workoutService.delete(workoutDTO.getId());
                deleteDialog.close();
                UI.getCurrent().getPage().getHistory().back();
            });

            Button cancel = new Button("Cancel", e -> deleteDialog.close());
            cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            cancel.addClickShortcut(Key.ESCAPE);

            HorizontalLayout deleteAndCancel = new HorizontalLayout(delete, cancel);
            deleteAndCancel.addClassNames("justify-between", "mt-m");
            deleteDialog.add(deleteAndCancel);

            Button openDelete = new Button("Delete my workout", e -> deleteDialog.open());
            openDelete.addClassNames("bg-error", "text-error-contrast");
            setHorizontalComponentAlignment(Alignment.CENTER, openDelete);
            add(openDelete);
        } else {
            add(ratingBar);
        }


    }

    /**
     * Update rating stars appearance and rating in system & page
     * <p>0 - init value</p>
     * <p>>0 - add</p>
     * <p><0 - remove</p>
     *
     * @param value     to add
     * @param workoutId workout's id to update
     * @param userId    to add
     */
    private void initAndUpdateRatingButtons(String value, int userId, int workoutId) {
        Integer previousUserVote = workoutService.getVotedUserId(workoutId, userId);
        if (previousUserVote != null && !"0".equals(value)) {
            workoutService.updateRating(workoutId, userId, String.valueOf(-previousUserVote));
        }
        workoutService.updateRating(workoutId, userId, value);
        ratingBar.removeAll();
        if (previousUserVote == null) {
            previousUserVote = 0;
        }
        if ("0".equals(value)) {
            value = String.valueOf(previousUserVote);
        }
        for (int i = 1; i < 6; i++) {
            String STAR_SIZE = "33px";
            int inc = i;
            if (i <= Integer.parseInt(value)) {
                Icon star = VaadinIcon.STAR.create();
                star.setColor(STAR_COLOR);
                star.setSize(STAR_SIZE);
                ratingBar.addItem(star, e -> initAndUpdateRatingButtons(String.valueOf(inc), userId, workoutId));
            } else {
                Icon paleStar = VaadinIcon.STAR.create();
                String PALE_STAR_COLOR = "#54677F";
                paleStar.setColor(PALE_STAR_COLOR);
                paleStar.setSize(STAR_SIZE);
                ratingBar.addItem(paleStar, e -> initAndUpdateRatingButtons(String.valueOf(inc), userId, workoutId));
            }
        }
        workoutDTO = workoutService.getDTO(workoutId);
        if (workoutDTO == null) {
            UI.getCurrent().getPage().getHistory().back();
        } else {
            rating.setText("Rating: " + workoutDTO.getRating());
        }

    }

    private boolean isMobileDevice() {
        WebBrowser webBrowser = VaadinSession.getCurrent().getBrowser();
        return webBrowser.isAndroid() || webBrowser.isIPhone() || webBrowser.isWindowsPhone() || webBrowser.isSafari();
    }

}
