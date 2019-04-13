package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

import java.util.ResourceBundle;

@SpringView
abstract class PersonForm extends BasicWindowForm {

    PersonForm() {
        ResourceBundle bundle = MainUI.getResourceBundle();

        final TextField nameField = new TextField(bundle.getString("NameField"));
        final TextField surnameField = new TextField(bundle.getString("SurnameField"));
        final TextField patronymicField = new TextField(bundle.getString("PatronymicField"));
        final DateField birthdayField = new DateField(bundle.getString("BirthdayField"));
        birthdayField.setLocale(bundle.getLocale());

        getMainBody().addComponents(
                nameField,
                surnameField,
                patronymicField,
                birthdayField);
    }
}
