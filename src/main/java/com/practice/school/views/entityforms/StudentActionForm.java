package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Student;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;

import java.util.ResourceBundle;

@SpringView
abstract class StudentActionForm extends BasicWindowForm {

    private final DateField dateField;
    private final ComboBox<Student> studentField;

    StudentActionForm() {

        ResourceBundle bundle = MainUI.getResourceBundle();
        dateField = new DateField(bundle.getString("DateField"));
        studentField = new ComboBox<>(bundle.getString("StudentField"));
        dateField.setLocale(bundle.getLocale());

        getFormLayout().addComponents(
                studentField,
                dateField);
    }

    DateField getDateField() {
        return dateField;
    }

    ComboBox<Student> getStudentField() {
        return studentField;
    }
}
