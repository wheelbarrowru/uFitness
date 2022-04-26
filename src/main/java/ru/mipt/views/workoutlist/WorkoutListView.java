package ru.mipt.views.workoutlist;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.model.User;
import ru.mipt.data.repository.TagsRepository;
import ru.mipt.data.repository.UserRepository;
import ru.mipt.data.service.FindWorkoutsService;
import ru.mipt.data.service.TagsService;
import ru.mipt.data.service.WorkoutService;
import ru.mipt.security.AuthenticatedUser;
import ru.mipt.views.tagsgrid.TagsGrid;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;


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
public class WorkoutListView extends Main implements HasComponents, HasStyle {

    private OrderedList workoutContainer;
    private TagsGrid tagsGrid;
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

        for (WorkoutDTO workoutDTO : findWorkoutsService.findWorkoutsByTagsDTO(new ArrayList<>())) {
            workoutContainer.add(new WorkoutListViewCard(workoutDTO.getId(), workoutService));
        }
        tagsGrid.setButtonIconAndText(VaadinIcon.SEARCH.create(), "Search");
        tagsGrid.addClickListener(event -> {
            workoutContainer.removeAll();
            Set<WorkoutDTO> workoutDTOSet = findWorkoutsService.findWorkoutsByTagsDTO(tagsGrid.getTags());
            for (WorkoutDTO workoutDTO : workoutDTOSet) {
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
        addClassNames("workout-list-view", "max-w-screen-lg", "mx-auto", "pb-l", "px-l");

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames("items-center", "justify-between");

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

        Button createWorkout = new Button("Create workout");
        createWorkout.addClickListener(e -> UI.getCurrent().navigate("create-workout"));

        Button logout = new Button("Log out");
        logout.addClickListener(e -> authenticatedUser.logout());

        buttons.add(profile, createWorkout, logout);
        buttons.addClassNames("justify-end");

        /*
        ignore
        Select<String> sortBy = new Select<>();
        sortBy.setLabel("Sort by");
        sortBy.setItems("Popularity", "Newest first", "Oldest first");
        sortBy.setValue("Popularity");
        */
        workoutContainer = new OrderedList();
        workoutContainer.addClassNames("gap-m", "grid", "list-none", "m-0", "p-0");

        tagsGrid = new TagsGrid(tagsService);
        tagsGrid.setWidth("50%");
        tagsGrid.addClassNames("py-m");
        container.add(header, buttons);

        add(container, tagsGrid, workoutContainer);
    }
}