package com.example.views.createworkout;

import com.example.views.tagsgrid.TagsGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Create Workout")
@Route(value = "create-workout")
//@PermitAll
@AnonymousAllowed
public class CreateWorkoutView extends VerticalLayout {

    private static final Component HEADER = new H2("Create your workout");
    private static final Label TAGS_LABEL = new Label("Add tags to your workout");
    private static final Label BODY_LABEL = new Label("Add your workout's description");
    private static final Label NAME_LABEL = new Label("Add your workout's name");

    private final TextField name;
    private final TextArea body;
    private final Button button;

    private final TagsGrid tagsGrid;

    public CreateWorkoutView() {
        name = new TextField();

        body = new TextArea();
        body.setWidthFull();

        button = new Button("Publish");
        button.addClickListener(e -> System.out.println("Publish workout"));

        tagsGrid = new TagsGrid();
        tagsGrid.setWidth("50%");

        VerticalLayout info = new VerticalLayout(NAME_LABEL, name, TAGS_LABEL, tagsGrid, BODY_LABEL, body, button);
        info.setWidth("70%");

        setHorizontalComponentAlignment(Alignment.CENTER, HEADER);
        setHorizontalComponentAlignment(Alignment.CENTER, info);
        info.setHorizontalComponentAlignment(Alignment.CENTER, button);

        add(HEADER, info);
    }

}
