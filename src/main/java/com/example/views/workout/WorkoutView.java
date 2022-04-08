package com.example.views.workout;

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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import javax.annotation.security.PermitAll;

@PageTitle("Workout")
@Route(value = "workout")
//@PermitAll
@AnonymousAllowed
public class WorkoutView extends VerticalLayout {

    private Component header;
    private Label text;


    public WorkoutView() {
        header = new H2("name_of_workout");
        text = new Label("do hard");
        text.setWidth("70%");

        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setLabel("Please rate the workout");
        radioGroup.setItems("1", "2","3","4","5");
        HorizontalLayout rateButtons = new HorizontalLayout(radioGroup);
        radioGroup.addValueChangeListener(e -> System.out.println("ну надо что-то считать"));

        UnorderedList content = new UnorderedList(
                new ListItem("бицепс"),
                new ListItem("глаз")
        );

        content.add();

        Details details = new Details("Tags", content);
        details.setOpened(true);
        details.addThemeVariants(DetailsVariant.REVERSE);
        details.setWidth("70%");

        setSizeFull();

        setHorizontalComponentAlignment(Alignment.END);
        setHorizontalComponentAlignment(Alignment.CENTER, header);
        setHorizontalComponentAlignment(Alignment.CENTER, text);
        setHorizontalComponentAlignment(Alignment.CENTER,rateButtons);
        setHorizontalComponentAlignment(Alignment.CENTER,details);

        add(header,details, text, rateButtons);
    }

}
