package com.practice.school;

import com.practice.school.interfaces.CommonMethods;
import com.practice.school.views.LogonView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.ResourceBundle;

@SpringUI
@PushStateNavigation
@SpringView
public class MainUI extends UI implements View {

    private static final String LANGUAGE_EN = "en";
    private static final String COUNTRY_EN = "EN";

    private static final String LANGUAGE_RU = "ru";
    private static final String COUNTRY_RU = "RU";

    private static final Locale localeRU = new Locale(LANGUAGE_RU, COUNTRY_RU);
    private static final Locale localeEN = new Locale(LANGUAGE_EN, COUNTRY_EN);

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("bundle", localeRU);

    //#TODO нормальные куки заиплить
    private static boolean user = false;

    private GridLayout mainLayout;
    private CssLayout viewContainer = new CssLayout();

    @Autowired
    private SpringViewProvider viewProvider;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        initNavigator();
        setupLayout();
        addMenu();
        CommonMethods.checkUser();
    }

    public static boolean isUser() {
        return user;
    }

    public static void setUser(boolean user) {
        MainUI.user = user;
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

        setContent(mainLayout);
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
//        navigator.addView("", LogonView.class);
//        navigator.addView("main", MainWindowView.class);
//        navigator.addView("students", StudentsView.class);
//        navigator.addView("teachers", TeachersView.class);
//        navigator.addView("lessons", LessonsView.class);
//        navigator.addView("exams", ExamsView.class);
//        navigator.addView("payments", PaymentsView.class);
//        navigator.addView("student", StudentView.class);
//        navigator.addView("teacher", TeacherView.class);
//        navigator.addView("lesson", LessonView.class);
//        navigator.addView("exam", ExamView.class);
//        navigator.addView("payment", PaymentView.class);
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

        Button exit = new Button(resourceBundle.getString("ExitMenuKey"), e -> new LogonView());
        exit.addStyleNames(ValoTheme.BUTTON_QUIET, ValoTheme.MENU_ITEM);

        CssLayout menu = new CssLayout(title, studentsButton, teachersButton, lessonsButton, examsButton, paymentsButton, exit);

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
        exit.addStyleName("my-menu-styles");

        return menu;
    }
}
