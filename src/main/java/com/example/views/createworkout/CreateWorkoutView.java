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
    private final Component header;
    private TextField name;
    private TextArea body;
    private final Button button;
    private final Label tagsLabel;
    private final Label bodyLabel;
    private final Label nameLabel;

    private TagsGrid tagsGrid;

    public CreateWorkoutView() {
        header = new H2("Create your workout");
        nameLabel = new Label("Add your workout's name");
        name = new TextField();

        bodyLabel = new Label("Add your workout's description");
        body = new TextArea();
        body.setWidthFull();

        button = new Button("Publish");
        button.addClickListener(e -> System.out.println("Publish workout"));

        tagsLabel = new Label("Add tags to your workout");
        tagsGrid = new TagsGrid();
        tagsGrid.setWidth("50%");

        VerticalLayout info = new VerticalLayout(nameLabel, name, tagsLabel, tagsGrid, bodyLabel, body, button);
        info.setWidth("70%");

        info.setHorizontalComponentAlignment(Alignment.CENTER, button);
        setHorizontalComponentAlignment(Alignment.CENTER, info);
        setHorizontalComponentAlignment(Alignment.CENTER, header);

        add(header, info);
    }

}
