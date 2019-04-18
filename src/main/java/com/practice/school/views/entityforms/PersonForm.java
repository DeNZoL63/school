package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

import java.util.ResourceBundle;

abstract class PersonForm extends BasicWindowForm {

    private final TextField nameField;
    private final TextField surnameField;
    private final TextField patronymicField;
    private final DateField birthdayField;

    PersonForm() {
        ResourceBundle bundle = MainUI.getResourceBundle();

        nameField = new TextField(bundle.getString("NameField"));
        surnameField = new TextField(bundle.getString("SurnameField"));
        patronymicField = new TextField(bundle.getString("PatronymicField"));
        birthdayField = new DateField(bundle.getString("BirthdayField"));
        birthdayField.setLocale(bundle.getLocale());

        getFormLayout().addComponents(
                surnameField,
                nameField,
                patronymicField,
                birthdayField);
    }

    TextField getNameField() {
        return nameField;
    }

    TextField getSurnameField() {
        return surnameField;
    }

    TextField getPatronymicField() {
        return patronymicField;
    }

    DateField getBirthdayField() {
        return birthdayField;
    }
}
