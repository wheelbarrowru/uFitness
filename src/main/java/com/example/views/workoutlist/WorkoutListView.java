package com.example.views.workoutlist;

import com.example.views.tagsgrid.TagsGrid;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@PageTitle("Workout List")
@Route(value = "workout-list")
//@PermitAll
@AnonymousAllowed
public class WorkoutListView extends Main implements HasComponents, HasStyle {

    private OrderedList workoutContainer;
    private TagsGrid tagsGrid;
    private Button profile;
    private Button createWorkout;
    private Button logout;

    public WorkoutListView() {
        HorizontalLayout buttons = new HorizontalLayout();

        profile = new Button("Profile");
        profile.addClickListener(e -> UI.getCurrent().navigate("profile"));

        createWorkout = new Button("Create workout");
        createWorkout.addClickListener(e -> UI.getCurrent().navigate("create-workout"));

        logout = new Button("Log out");
        logout.addClickListener(e -> System.out.println("logout"));

        buttons.add(profile,createWorkout,logout);
        buttons.addClassNames("justify-end");

        add(buttons);

        constructUI();

        workoutContainer.add(new WorkoutListViewCard("part of body1"));
        workoutContainer.add(new WorkoutListViewCard("part of body2"));
        workoutContainer.add(new WorkoutListViewCard("part of body3"));

    }

    private void constructUI() {
        addClassNames("workout-list-view", "max-w-screen-lg", "mx-auto", "pb-l", "px-l");

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames("items-center", "justify-between");

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Workouts");
        header.addClassNames("mb-0", "mt-xl", "text-3xl");
        Paragraph description = new Paragraph("Royalty free photos and pictures, courtesy of Unsplash");
        description.addClassNames("mb-xl", "mt-0", "text-secondary");
        headerContainer.add(header, description);

        Select<String> sortBy = new Select<>();
        sortBy.setLabel("Sort by");
        sortBy.setItems("Popularity", "Newest first", "Oldest first");
        sortBy.setValue("Popularity");

        workoutContainer = new OrderedList();
        workoutContainer.addClassNames("gap-m", "grid", "list-none", "m-0", "p-0");

        tagsGrid = new TagsGrid();
        tagsGrid.setWidth("50%");
        tagsGrid.addClassNames("py-m");
        container.add(header, sortBy);

        add(container, tagsGrid, workoutContainer);
    }
}