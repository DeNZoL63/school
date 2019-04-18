package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

@UIScope
@SpringView(name = "teacher")
public class TeacherView extends PersonForm {

    private ResourceBundle bundle;

    @Autowired
    public TeacherView() {
        customForm();
        fillForm();
    }

    private void fillForm() {
        FormLayout formLayout = getFormLayout();

        final TextField experienceField = new TextField(bundle.getString("ExperienceField"));
        final TextField licenseNumberField = new TextField(bundle.getString("LicenseNumberField"));

        formLayout.addComponents(
                experienceField,
                licenseNumberField);
    }

    private void customForm(){
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
