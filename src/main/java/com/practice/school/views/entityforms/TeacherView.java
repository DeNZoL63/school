package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

public class TeacherView extends PersonForm {

    private ResourceBundle bundle;

    @Autowired
    public TeacherView() {
        super();
        VerticalLayout form = getForm();
        customForm();
        fillForm();
        setContent(form);
    }

    private void fillForm() {
        FormLayout formLayout = getMainBody();

        final TextField experienceField = new TextField(bundle.getString("ExperienceField"));
        final TextField licenseNumberField = new TextField(bundle.getString("LicenseNumberField"));

        formLayout.addComponents(
                experienceField,
                licenseNumberField);
    }

    @Override
    public Component getViewComponent() {
        return new VerticalLayout();
    }

    private void customForm(){
        setWindowMode(WindowMode.MAXIMIZED);
        this.setModal(true);
        setClosable(false);
        setResizable(false);

        UI.getCurrent().addWindow(this);
        bundle = MainUI.getResourceBundle();
        UI.getCurrent().getPage().setTitle(bundle.getString("TitleFormTeacher"));
        setHeaderTitle(bundle.getString("TitleFormTeacher"));
    }

    @Override
    public void applyAction() {
        closeForm();
    }

    @Override
    public void okAction() {
        closeForm();
    }

    @Override
    public void cancelAction() {
        closeForm();
    }

    private void closeForm() {
        close();
        MainUI.getCurrent().getNavigator().navigateTo("teachers");
    }
}
