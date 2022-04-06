package com.example.views.startpage;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Start Page")
@Route(value = "start-page")
@RouteAlias(value = "")
@AnonymousAllowed
public class StartPageView extends VerticalLayout {

    private final Component welcomeToUFitness = new H1("Welcome to uFitness");
    private final Button login;
    private final Button registration;

    public StartPageView() {
        login = new Button("Sign in");
        registration = new Button("Sign up");
        login.addClickListener(e -> UI.getCurrent().navigate("login"));
        registration.addClickListener(e -> UI.getCurrent().navigate("registration"));

        setSizeFull();
        setJustifyContentMode ( FlexComponent.JustifyContentMode.CENTER );

        login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        login.setSizeFull();
        registration.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registration.setSizeFull();

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        buttonLayout.add(login);
        buttonLayout.add(registration);
        buttonLayout.setWidth("400px");

        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, welcomeToUFitness);
        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, buttonLayout);

        add(welcomeToUFitness, buttonLayout);
    }

}
