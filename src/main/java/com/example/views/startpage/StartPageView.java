package com.example.views.startpage;

import com.vaadin.flow.component.Component;
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

/**
 *The main view that holds the start-page form
 * <p>
 * This view is itself a component (specifically a VerticalLayout) to
 * which the start-page form is added. This view is made accessible
 * to the end-user via the @Route annotation.
 * <p>
 * A new instance of this class opens every time you starts web application
 * browser tab/window.
 */
@PageTitle("Start Page")
@Route(value = "start-page")
@RouteAlias(value = "")
@AnonymousAllowed
public class StartPageView extends VerticalLayout {

    private final Button login;
    private final Button registration;

    /**
     * Constructor - creating a new view for start-page
     */
    public StartPageView() {
        login = new Button("Sign in");
        registration = new Button("Sign up");
        login.addClickListener(e -> login.getUI().ifPresent(ui -> ui.navigate("login")));
        registration.addClickListener(e -> registration.getUI().ifPresent(ui -> ui.navigate("registration")));

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

        Component welcomeToUFitness = new H1("Welcome to uFitness");
        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, welcomeToUFitness);
        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, buttonLayout);

        add(welcomeToUFitness, buttonLayout);
    }

}
