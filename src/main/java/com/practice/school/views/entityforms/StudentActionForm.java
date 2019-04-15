package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Student;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;

import java.util.ResourceBundle;

@SpringView
abstract class StudentActionForm extends BasicWindowForm {

    StudentActionForm() {

        ResourceBundle bundle = MainUI.getResourceBundle();
        final DateField dateField = new DateField(bundle.getString("DateField"));
        final ComboBox<Student> studentField = new ComboBox<>(bundle.getString("StudentField"));
        dateField.setLocale(bundle.getLocale());

        getMainBody().addComponents(
                studentField,
                dateField);
    }

}
