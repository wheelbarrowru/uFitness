package com.example.views.login;

import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * * Front of login class  with <b>ERROR_MESSAGE</b> and <b>restClientService</b> and <b>authenticatedUser</b> properties.
 * * This class creates a tab front login
 * <p>Basic LoginI18n by Vaadin</p>
 */
@PageTitle("Login")
@Route(value = "login")
public class LoginView extends LoginOverlay {
    /**
     * Constructor - creating a front of login page
     */
    public LoginView() {
        setAction("login");

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("uFitness");
        i18n.getHeader().setDescription("Login using user/user or admin/admin");
        i18n.setAdditionalInformation(null);
        setI18n(i18n);

        setForgotPasswordButtonVisible(false);
        setOpened(true);
    }
}
