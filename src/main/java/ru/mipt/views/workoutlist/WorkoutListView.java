package ru.mipt.views.workoutlist;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.model.User;
import ru.mipt.data.repository.TagsRepository;
import ru.mipt.data.repository.UserRepository;
import ru.mipt.data.service.FindWorkoutsService;
import ru.mipt.data.service.TagsService;
import ru.mipt.data.service.WorkoutService;
import ru.mipt.security.AuthenticatedUser;
import ru.mipt.views.tagsgrid.TagsGrid;

import javax.annotation.security.RolesAllowed;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * The main view that holds the Workout list form
 * <p>
 * This view is itself a component (specifically a VerticalLayout) to
 * which the workout list form is added. This view is made accessible
 * to the end-user via the @Route annotation.
 */
@PageTitle("Workout List")
@Route(value = "workout-list")
@RolesAllowed("USER")
public class WorkoutListView extends Div implements HasComponents, HasStyle {

    private OrderedList workoutContainer;
    private TagsGrid tagsGrid;
    private TextField search;
    private final TagsService tagsService;
    private final AuthenticatedUser authenticatedUser;

    /**
     * Constructor of WorkoutListView
     * <p>
     * This method create new Authenticated user, calls constructUI method
     *
     * @param tagsService         basic service
     * @param workoutService      basic service
     * @param findWorkoutsService basic service
     * @param userRepository      basic service
     * @see WorkoutListView#constructUI()
     * @see UserRepository
     * @see TagsService#TagsService(TagsRepository)
     */
    public WorkoutListView(@Autowired TagsService tagsService, @Autowired WorkoutService workoutService, @Autowired FindWorkoutsService findWorkoutsService, @Autowired UserRepository userRepository) {
        this.tagsService = tagsService;
        this.authenticatedUser = new AuthenticatedUser(userRepository);

        constructUI();

        Set<WorkoutDTO> workoutDTOSet0 = findWorkoutsService.findWorkoutsByTitleAndTagsDTO(search.getValue(), tagsGrid.getTags());
        for (WorkoutDTO workoutDTO : workoutDTOSet0.stream().sorted().collect(Collectors.toList())) {
            workoutContainer.add(new WorkoutListViewCard(workoutDTO.getId(), workoutService));
        }

        search.addValueChangeListener(event -> {
                    workoutContainer.removeAll();
                    Set<WorkoutDTO> workoutDTOSet = findWorkoutsService.findWorkoutsByTitleAndTagsDTO(search.getValue(), tagsGrid.getTags());
                    for (WorkoutDTO workoutDTO : workoutDTOSet.stream().sorted().collect(Collectors.toList())) {
                        workoutContainer.add(new WorkoutListViewCard(workoutDTO.getId(), workoutService));
                    }
                    if (workoutDTOSet.isEmpty()) {
                        Div hint = new Div();
                        hint.setText("There are no workouts with the given tags");
                        hint.getStyle().set("padding", "var(--lumo-size-l)")
                                .set("text-align", "center")
                                .set("color", "var(--lumo-contrast-70pct)");
                        workoutContainer.add(new ListItem(hint));
                    }
                }
        );


        tagsGrid.addClickListener(event -> {
            workoutContainer.removeAll();
            Set<WorkoutDTO> workoutDTOSet = findWorkoutsService.findWorkoutsByTitleAndTagsDTO(search.getValue(), tagsGrid.getTags());
            for (WorkoutDTO workoutDTO : workoutDTOSet.stream().sorted().collect(Collectors.toList())) {
                workoutContainer.add(new WorkoutListViewCard(workoutDTO.getId(), workoutService));
            }
            if (workoutDTOSet.isEmpty()) {
                workoutContainer.add(new ListItem(new Text("There are no workouts with the given tags")));
            }

        });


    }

    /**
     * Method which create User Interface adding buttons for going to profile or workouts
     * This method creates tagsGrid by tagsService
     *
     * @see TagsGrid#TagsGrid(TagsService)
     */
    private void constructUI() {
        addClassNames("workout-list-view", "max-w-screen-2xl", "mx-xl", "pb-l", "px-m");

        HorizontalLayout headerAndButtons = new HorizontalLayout();
        headerAndButtons.addClassNames("items-center", "justify-between", "px-l");

        H2 header = new H2("Workouts");
        header.addClassNames("mb-0", "mt-xl", "text-3xl");

        HorizontalLayout buttons = new HorizontalLayout();
        String id = "?error";
        Optional<User> user = authenticatedUser.get();
        try {
            id = String.valueOf(user.orElseThrow().getId());
        } catch (NoSuchElementException ignored) {
        }

        String address = "profile/" + id;
        Button profile = new Button("Profile");
        profile.addClickListener(e -> UI.getCurrent().navigate(address));
        profile.addThemeVariants(ButtonVariant.LUMO_LARGE);

        Button createWorkout = new Button("Create workout");
        createWorkout.addClickListener(e -> UI.getCurrent().navigate("create-workout"));
        createWorkout.addThemeVariants(ButtonVariant.LUMO_LARGE);

        Button logout = new Button("Log out");
        logout.addClickListener(e -> authenticatedUser.logout());
        logout.addThemeVariants(ButtonVariant.LUMO_LARGE);

        buttons.add(profile, createWorkout, logout);
        buttons.addClassNames("justify-end");

        workoutContainer = new OrderedList();
        workoutContainer.addClassNames("gap-m", "grid", "list-none", "m-0", "px-l");

        tagsGrid = new TagsGrid(tagsService);
        tagsGrid.setWidth("50%");
        tagsGrid.addClassNames("p-m");
        headerAndButtons.add(header, buttons);

        search = new TextField();
        search.setPlaceholder("Workout name");
        search.setWidth("60%");
        search.setClearButtonVisible(true);

        Button searchButton = new Button("Search");
        searchButton.setIcon(VaadinIcon.SEARCH.create());
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout searchLayout = new HorizontalLayout(search, searchButton);
        searchLayout.addClassNames("p-l");

        Main searchAndWorkouts = new Main(searchLayout, workoutContainer);

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.addToPrimary(searchAndWorkouts);
        splitLayout.addToSecondary(tagsGrid);
        splitLayout.setSplitterPosition(72);
        add(headerAndButtons, splitLayout);
    }
}