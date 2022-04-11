package com.example.views.createworkout;

import com.example.data.dto.TagsDTO;
import com.example.data.service.TagsService;
import com.example.views.tagsgrid.TagsGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class CreateWorkoutForm extends FormLayout {
    private final Component header;
    private TextField title;
    private TextArea body;

    private final Button button;

    private final Span errorMessageField;

    private TagsGrid tagsGrid;

    public CreateWorkoutForm(TagsService tagsService) {
        header = new H2("Create your workout");
        title = new TextField("Add your workout's title");

        errorMessageField = new Span();

        body = new TextArea("Add your workout's description");
        body.setWidthFull();

        button = new Button("Publish");
        button.setMaxWidth("120px");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        tagsGrid = new TagsGrid(tagsService);
        tagsGrid.setMaxWidth("320px");

        setRequiredIndicatorVisible(title, body);

        VerticalLayout headerLayout = new VerticalLayout(header);
        headerLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,header);
        headerLayout.setWidthFull();

        VerticalLayout buttonLayout = new VerticalLayout(button);
        buttonLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,button);
        buttonLayout.setWidthFull();
        tagsGrid.addClassName("m-s");
        add(headerLayout, title, tagsGrid, body,errorMessageField, buttonLayout);

        setColspan(header,2);
        setColspan(title,2);
        setColspan(tagsGrid,2);
        setColspan(body,2);
        setColspan(errorMessageField,2);
        setColspan(buttonLayout,2);
        setColspan(headerLayout,2);


        setMaxWidth("800px");
    }
    public Button getButton() {
        return button;
    }

    public Span getErrorMessageField() {
        return errorMessageField;
    }

    public TagsGrid getTagsGrid() {
        return tagsGrid;
    }

    public TextField getTitle() {
        return title;
    }

    public TextArea getBody() {
        return body;
    }

    public Set<TagsDTO> getTagsSet() {
        return new HashSet<>(tagsGrid.getTags());
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
