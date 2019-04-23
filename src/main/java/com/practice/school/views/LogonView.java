package com.practice.school.views;

import com.practice.school.MainUI;
import com.practice.school.interfaces.OkCancelActions;
import com.practice.school.views.elements.LocaleElement;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ResourceBundle;

@UIScope
@SpringView(name = "login")
public class LogonView extends Window implements View, OkCancelActions {

    private VerticalLayout mainLayout;
    private ResourceBundle resourceBundle;

    public LogonView() {
        init();
    }

    @Override
    public Component getViewComponent() {
        return new VerticalLayout();
    }

    private void init() {

        resourceBundle = MainUI.getResourceBundle();

        setupLayout();
        createForm();

        setWindowMode(WindowMode.MAXIMIZED);
        this.setModal(true);
        setClosable(false);
        setResizable(false);
        UI.getCurrent().addWindow(this);

        ResourceBundle bundle = MainUI.getResourceBundle();
        UI.getCurrent().getPage().setTitle(bundle.getString("AuthorizationHeaderText"));
    }

    private void setupLayout() {
        mainLayout = new VerticalLayout();
        HorizontalLayout buttonsLocale = LocaleElement.getButtonsGroup();
        mainLayout.addComponent(buttonsLocale);
        mainLayout.setExpandRatio(buttonsLocale, 0.05f);
        mainLayout.setComponentAlignment(buttonsLocale, Alignment.TOP_RIGHT);

        setContent(mainLayout);
    }

    private void createForm() {

        TextField login = new TextField(resourceBundle.getString("UsernameField"));
        login.setWidth("300px");
        PasswordField password = new PasswordField(resourceBundle.getString("PasswordField"));
        password.setWidth("300px");

        Button signInButton = new Button(resourceBundle.getString("EnterKey"));
        Button cancelButton = new Button(resourceBundle.getString("CancelKey"));

        signInButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        signInButton.addClickListener((Button.ClickListener) clickEvent -> okAction());
        signInButton.setId("okButton");

        cancelButton.addClickListener((Button.ClickListener) clickEvent -> cancelAction());
        cancelButton.setId("cancelButton");
        cancelButton.setStyleName(ValoTheme.BUTTON_QUIET);

        HorizontalLayout buttonsGroup = new HorizontalLayout();
        buttonsGroup.addComponents(signInButton, cancelButton);

        FormLayout loginForm = new FormLayout();
        loginForm.addComponents(login, password, buttonsGroup);

        Panel loginPanel = new Panel(resourceBundle.getString("PanelName"));
        loginPanel.setContent(loginForm);
        loginPanel.setWidthUndefined();

        mainLayout.addComponent(loginPanel);
        mainLayout.setExpandRatio(loginPanel, 0.95f);
        mainLayout.setSizeFull();
        mainLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void okAction() {
        //#TODO проверка корректности логина и пароля
        Notification.show("Нажали ВХОД");

        MainUI.setUser(true);
        close();
        Navigator navi = MainUI.getCurrent().getNavigator();

        String destanation = navi.getState();
        if (navi.getState().equals("logon")){
            destanation = "";
        }
        navi.navigateTo(destanation);
    }

    @Override
    public void cancelAction() {
        MainUI.setUser(false);
        close();
        UI.getCurrent().close();
    }
}
