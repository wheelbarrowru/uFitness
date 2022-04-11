package com.example.views.profile;

import com.example.data.dto.UserDTO;
import com.example.data.service.RestClientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.example.data.service.UserService;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

@PageTitle("Profile")
@Route(value = "profile")
//@RolesAllowed("USER")
@AnonymousAllowed
public class ProfileView extends Div implements HasUrlParameter<Integer> {

    private final UserService userService;
    private final RestClientService restClientService;

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer param) {
        addProfileForm(param);
    }

    public void addProfileForm(Integer param){
        ProfileForm profileForm = new ProfileForm(userService,restClientService,param);
        add(profileForm);
    }

    public ProfileView(@Autowired UserService userService,
                       @Autowired RestClientService restClientService) {
        this.userService = userService;
        this.restClientService=restClientService;
    }




}
