package com.example.views.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import elemental.json.impl.JsonUtil;

@PageTitle("Login")
@Route(value = "login")
public class LoginView extends LoginOverlay {
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

        addLoginListener(e -> System.out.println("lol"));
        //addLoginListener(ComponentEventListener< AbstractLogin.LoginEvent> listener);

        System.out.println(i18n.getForm().getPassword()+" "+ i18n.getAdditionalInformation());
        System.out.println(i18n.getForm().getSubmit());
    }

}
