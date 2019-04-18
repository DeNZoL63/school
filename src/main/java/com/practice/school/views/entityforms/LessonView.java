package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Teacher;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

@UIScope
@SpringView(name = "lesson")
public class LessonView extends StudentActionForm {

    private ResourceBundle bundle;

    @Autowired
    public LessonView() {
        customForm();
        fillForm();
    }

    private void fillForm() {
        FormLayout formLayout = getFormLayout();

        final DateField timeField = new DateField(bundle.getString("TimeField"));
        final ComboBox<Teacher> teacherField = new ComboBox<>(bundle.getString("TeacherField"));
        final TextField gpsField = new TextField(bundle.getString("GPSField"));
        timeField.setLocale(bundle.getLocale());
        timeField.setDateFormat("hh:mm");

        formLayout.addComponents(
                timeField,
                teacherField,
                gpsField);
    }

    private void customForm(){
        bundle = MainUI.getResourceBundle();
        UI.getCurrent().getPage().setTitle(bundle.getString("TitleFormLesson"));
        setHeaderTitle(bundle.getString("TitleFormLesson"));
    }

    private void closeForm() {
        close();
        MainUI.getCurrent().getNavigator().navigateTo("lessons");
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
}
