package com.example.views.workoutlist;

import com.example.data.dto.WorkoutDTO;
import com.example.data.model.User;
import com.example.data.repository.UserRepository;
import com.example.data.service.FindWorkoutsService;
import com.example.data.service.TagsService;
import com.example.data.service.WorkoutService;
import com.example.security.AuthenticatedUser;
import com.example.views.tagsgrid.TagsGrid;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.sql.SQLOutput;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;


@PageTitle("Workout List")
@Route(value = "workout-list")
@RolesAllowed("USER")
//@AnonymousAllowed
public class WorkoutListView extends Main implements HasComponents, HasStyle {

    private OrderedList workoutContainer;
    private TagsGrid tagsGrid;
    private Button profile;
    private Button createWorkout;
    private Button logout;
    private final TagsService tagsService;
    private final AuthenticatedUser authenticatedUser;

    @Autowired
    public WorkoutListView(TagsService tagsService, WorkoutService workoutService,
                           FindWorkoutsService findWorkoutsService, UserRepository userRepository) {
        this.tagsService = tagsService;
        this.authenticatedUser = new AuthenticatedUser(userRepository);


        constructUI();

        tagsGrid.addClickListener(event -> {
            workoutContainer.removeAll();
            Set<WorkoutDTO> workoutDTOSet = findWorkoutsService.findWorkoutsByTagsDTO(tagsGrid.getTags());
            for (WorkoutDTO workoutDTO : workoutDTOSet) {
                workoutContainer.add(new WorkoutListViewCard(workoutDTO.getId(), workoutService));
            }
        });


    }

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
        } catch (NoSuchElementException ignored) { }

        String address = "profile/" + id;
        profile = new Button("Profile");
        profile.addClickListener(e -> UI.getCurrent().navigate(address));

        createWorkout = new Button("Create workout");
        createWorkout.addClickListener(e -> UI.getCurrent().navigate("create-workout"));

        logout = new Button("Log out");
        logout.addClickListener(e -> authenticatedUser.logout());

        buttons.add(profile, createWorkout, logout);
        buttons.addClassNames("justify-end");

        /*
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