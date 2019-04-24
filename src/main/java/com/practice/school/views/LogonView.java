package com.practice.school.views;

import com.practice.school.MainUI;
import com.practice.school.interfaces.OkCancelActions;
import com.practice.school.views.elements.LocaleElement;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ResourceBundle;

@UIScope
@SpringView(name = "login")
public class LogonView extends VerticalLayout implements View, OkCancelActions{

    private VerticalLayout mainLayout;
    private ResourceBundle resourceBundle;
    private LoginCallback callback;
    private TextField login;
    private PasswordField password;

    public LogonView(LoginCallback callback) {
        this.callback = callback;
        init();
    }

    private void init() {
        resourceBundle = MainUI.getResourceBundle();

        setupLayout();
        createForm();

        ResourceBundle bundle = MainUI.getResourceBundle();
        UI.getCurrent().getPage().setTitle(bundle.getString("AuthorizationHeaderText"));

        addComponent(mainLayout);
    }

    private void setupLayout() {
        mainLayout = new VerticalLayout();
        HorizontalLayout buttonsLocale = LocaleElement.getButtonsGroup();
        mainLayout.addComponent(buttonsLocale);
        mainLayout.setExpandRatio(buttonsLocale, 0.05f);
        mainLayout.setComponentAlignment(buttonsLocale, Alignment.TOP_RIGHT);
    }

    private void createForm() {
        login = new TextField(resourceBundle.getString("UsernameField"));
        login.setWidth("300px");
        password = new PasswordField(resourceBundle.getString("PasswordField"));
        password.setWidth("300px");

        Button signInButton = new Button(resourceBundle.getString("EnterKey"));
        Button cancelButton = new Button(resourceBundle.getString("CancelKey"));

        signInButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        signInButton.addClickListener(event -> okAction());
        signInButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        signInButton.setId("okButton");

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

    @FunctionalInterface
    public interface LoginCallback {
        boolean login(String username, String password);
    }

    @Override
    public void okAction() {
        String pword = password.getValue();
        password.setValue("");
        if (!callback.login(login.getValue(), pword)) {
            //#TODO need localization
            Notification.show("Login failed");
            login.focus();
        }
    }

    @Override
    public void cancelAction() {
        login.setValue("");
        password.setValue("");
    }
}
