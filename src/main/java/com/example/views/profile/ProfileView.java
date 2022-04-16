package com.example.views.profile;

import com.example.data.service.RestClientService;
import com.example.security.AuthenticatedUser;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.util.NoSuchElementException;

@PageTitle("Profile")
@Route(value = "profile")
@RolesAllowed("USER")
//@AnonymousAllowed
public class ProfileView extends Div implements HasUrlParameter<Integer> {
    private final String ERROR_MESSAGE = "You haven't access to this page";
    private final RestClientService restClientService;
    private final AuthenticatedUser authenticatedUser;

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer param) {
        addProfileForm(param);
    }

    public void addProfileForm(Integer param){
        H2 errorMessage = new H2(ERROR_MESSAGE);
        try {
            if (authenticatedUser.get().orElseThrow().getId() == param) {
                ProfileForm profileForm = new ProfileForm(restClientService, authenticatedUser, param);
                add(profileForm);
            } else {
                add(errorMessage);
            }
        } catch (NoSuchElementException e){
            add(errorMessage);
        }

    }

    public ProfileView(@Autowired RestClientService restClientService,
                       @Autowired AuthenticatedUser authenticatedUser) {
        this.restClientService=restClientService;
        this.authenticatedUser = authenticatedUser;
    }




}
