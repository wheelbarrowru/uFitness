package com.example.views.createworkout;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import javax.annotation.security.PermitAll;

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
    private final Button exit;

    private static Grid<String> tags;

    public CreateWorkoutView() {
        header = new H2("Creating your workout");
        nameLabel = new Label("Add your workout's name");
        name = new TextField();

        bodyLabel = new Label("Add your workout's description");
        body = new TextArea();
        body.setWidthFull();

        button = new Button("Publish");

        tagsLabel = new Label("Add tags to your workout");
        tags = new Grid<>(String.class, false);
        tags.setAllRowsVisible(true);

        exit = new Button("Exit");
        exit.addClickListener(e -> UI.getCurrent().navigate("start-page"));
        exit.setWidth("10%");
        exit.setHeight("40px");
        exit.addThemeVariants(ButtonVariant.LUMO_SMALL);

        setHorizontalComponentAlignment(Alignment.CENTER, header);

        VerticalLayout info = new VerticalLayout(nameLabel, name, tagsLabel, tags, bodyLabel, body, button);
        info.setWidth("70%");
        info.setHorizontalComponentAlignment(Alignment.END, exit);
        info.setHorizontalComponentAlignment(Alignment.CENTER, button);
        setHorizontalComponentAlignment(Alignment.CENTER, info);

        add(exit, header, info);
    }

}
