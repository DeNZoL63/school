package com.practice.school;

import com.practice.school.security.SecurityUtils;
import com.practice.school.views.LogonView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Locale;
import java.util.ResourceBundle;

@SpringUI
@PushStateNavigation
@SpringView
public class MainUI extends UI implements View {

    @Autowired
    AuthenticationManager authenticationManager;

    private static final String LANGUAGE_EN = "en";
    private static final String COUNTRY_EN = "EN";

    private static final String LANGUAGE_RU = "ru";
    private static final String COUNTRY_RU = "RU";

    private static final Locale localeRU = new Locale(LANGUAGE_RU, COUNTRY_RU);
    private static final Locale localeEN = new Locale(LANGUAGE_EN, COUNTRY_EN);

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("bundle", localeRU);

    private GridLayout mainLayout;
    private CssLayout viewContainer = new CssLayout();

    @Autowired
    private SpringViewProvider viewProvider;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        initNavigator();
        setupLayout();
        addMenu();
        if (SecurityUtils.isLoggedIn()) {
            setContent(mainLayout);
        } else {
            setContent(new LogonView(this::login));
        }
    }

    public static void setResourceBundleRU() {
        resourceBundle = ResourceBundle.getBundle("bundle", localeRU);
    }

    public static void setResourceBundleEN() {
        resourceBundle = ResourceBundle.getBundle("bundle", localeEN);
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setViewProvider(SpringViewProvider viewProvider) {
        this.viewProvider = viewProvider;
    }

    private void setupLayout() {
        mainLayout = new GridLayout(2, 1);
        viewContainer.setSizeFull();
    }

    private void addMenu() {
        CssLayout menu = getMenu();

        Panel panel = new Panel();
        panel.setContent(menu);
        panel.setWidthUndefined();
        panel.setHeight("100%");

        mainLayout.addComponent(panel, 0, 0);
        mainLayout.addComponent(viewContainer, 1, 0);
        mainLayout.setColumnExpandRatio(0, 1);
        mainLayout.setColumnExpandRatio(1, 99);

        mainLayout.setSpacing(false);
        mainLayout.setSizeFull();
    }

    private void initNavigator() {
        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
    }

    private CssLayout getMenu() {

        Button title = new Button(resourceBundle.getString("MainMenuKey"), e -> getNavigator().navigateTo(""));
        title.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_ITEM);

        Button studentsButton = new Button(resourceBundle.getString("StudentsMenuKey"), e -> getNavigator().navigateTo("students"));
        studentsButton.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_ITEM);

        Button teachersButton = new Button(resourceBundle.getString("TeachersMenuKey"), e -> getNavigator().navigateTo("teachers"));
        teachersButton.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_ITEM);

        Button lessonsButton = new Button(resourceBundle.getString("LessonsMenuKey"), e -> getNavigator().navigateTo("lessons"));
        lessonsButton.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_ITEM);

        Button examsButton = new Button(resourceBundle.getString("ExamsMenuKey"), e -> getNavigator().navigateTo("exams"));
        examsButton.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_ITEM);

        Button paymentsButton = new Button(resourceBundle.getString("PaymentsMenuKey"), e -> getNavigator().navigateTo("payments"));
        paymentsButton.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_ITEM);

        Button exit = new Button(resourceBundle.getString("ExitMenuKey"), e -> logout());
        exit.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_ITEM);

        CssLayout menu = new CssLayout(
                title,
                studentsButton,
                teachersButton,
                lessonsButton,
                examsButton,
                paymentsButton,
                exit);

        UI.getCurrent().getPage().getStyles().add(
                ".my-menu-style \n" +
                " .valo .valo-menu-item:focus, .valo .valo-menu-item:hover, .valo .valo-menu-item.selected {\n" +
                    " color: #3c66ca;\n" +
                    "}\n" +
                " .valo .v-button {\n" +
                    " background-image: linear-gradient(to bottom,#b5e7fb 2%, #81cff5 98%);\n" +
                    " text-align: left;\n" +
                "}");

        menu.addStyleName("my-menu-style");

        return menu;
    }

    private void logout() {
        getPage().reload();
        getSession().close();
    }

    private boolean login(String username, String password) {
        try {
            Authentication token = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // Reinitialize the session to protect against session fixation attacks. This does not work
            // with websocket communication.
            VaadinService.reinitializeSession(VaadinService.getCurrentRequest());
            SecurityContextHolder.getContext().setAuthentication(token);
            // Now when the session is reinitialized, we can enable websocket communication. Or we could have just
            // used WEBSOCKET_XHR and skipped this step completely.
            getPushConfiguration().setTransport(Transport.WEBSOCKET);
            getPushConfiguration().setPushMode(PushMode.AUTOMATIC);
            // Show the main UI
            setContent(mainLayout);
            return true;
        } catch (AuthenticationException ex) {
            return false;
        }
    }
}
