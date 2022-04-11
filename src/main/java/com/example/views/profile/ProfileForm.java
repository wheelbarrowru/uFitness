package com.example.views.profile;

import com.example.data.dto.UserDTO;
import com.example.data.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class ProfileForm extends VerticalLayout {
    private Button logout;
    private Button delete;

    private final H2 header = new H2("Your profile");
    private final Label usernameLabel = new Label("Username: ");
    private final Label firstNameLabel = new Label("First name: ");
    private final Label lastNameLabel = new Label("Second name: ");
    private final Label emailLabel = new Label("Email: ");
    private Label username;
    private Label firstName;
    private Label email;

    public ProfileForm(UserService userService, Integer param){
        UserDTO userDto = userService.getDTO(param);

        usernameLabel.addClassNames("text-l");
        firstNameLabel.addClassNames("text-l");
        lastNameLabel.addClassNames("text-l");
        emailLabel.addClassNames("text-l");

        username = new Label(userDto.getUsername());
        username.addClassNames("text-l");
        firstName = new Label(userDto.getFirstName());
        firstName.addClassNames("text-l");
        Label lastName = new Label("2name");
        lastName.addClassNames("text-l");
        email = new Label(userDto.getEmail());
        email.addClassNames("text-l");
        logout = new Button("Log out");
        delete = new Button("Delete my account");
        delete.addClassNames("bg-error", "text-error-contrast");
        logout.setWidthFull();
        delete.setWidthFull();


        setSizeFull();
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        VerticalLayout context = new VerticalLayout();
        context.add(new HorizontalLayout(usernameLabel, username));
        context.add(new HorizontalLayout(firstNameLabel, firstName));
        context.add(new HorizontalLayout(lastNameLabel, lastName));
        context.add(new HorizontalLayout(emailLabel, email));
        context.setWidth("450px");

        HorizontalLayout buttons = new HorizontalLayout(logout, delete);
        buttons.addClassNames("justify-between", "self-end");
        buttons.setWidthFull();

        HorizontalLayout space = new HorizontalLayout();
        context.add(space);

        setHorizontalComponentAlignment(Alignment.CENTER, context);
        setHorizontalComponentAlignment(Alignment.CENTER, buttons);
        setHorizontalComponentAlignment(Alignment.CENTER, header);
        context.add(buttons);
        add(header, context);
    }
}
