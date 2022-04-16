package com.example.views.workout;

import com.example.data.dto.TagsDTO;
import com.example.data.dto.WorkoutDTO;
import com.example.data.service.WorkoutService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;

public class WorkoutForm extends VerticalLayout {


    public WorkoutForm(WorkoutService workoutService, int workoutID) {
        WorkoutDTO workoutDTO = workoutService.getDTO(workoutID);
        Component header = new H2(workoutDTO.getTitle());
        Label rating = new Label("Rating: " + workoutDTO.getRating());

        TextArea text = new TextArea();
        text.setValue(workoutDTO.getDescription());
        text.setReadOnly(true);
        text.setWidth("70%");

        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setLabel("Please rate the workout");
        radioGroup.setItems("1", "2","3","4","5");
        HorizontalLayout rateButtons = new HorizontalLayout(radioGroup);
        radioGroup.addValueChangeListener(e -> workoutService
                .updateRating(workoutDTO, radioGroup.getValue()));

        UnorderedList content = new UnorderedList();
        for(TagsDTO tagsDTO: workoutDTO.getWorkoutTags()){
            content.add(new ListItem(tagsDTO.getMessage()));
        }

        content.add();

        Details details = new Details("Tags", content);
        details.setOpened(true);
        details.addThemeVariants(DetailsVariant.REVERSE);
        details.setWidth("70%");

        setSizeFull();

        setHorizontalComponentAlignment(Alignment.CENTER, header);
        setHorizontalComponentAlignment(Alignment.CENTER, rating);
        setHorizontalComponentAlignment(Alignment.CENTER, text);
        setHorizontalComponentAlignment(Alignment.CENTER,rateButtons);
        setHorizontalComponentAlignment(Alignment.CENTER,details);

        add(header, rating, details, text, rateButtons);
    }

}
