package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

import java.util.ResourceBundle;

//@SpringView
//@SpringComponent
abstract class PersonForm extends BasicWindowForm {

    private final TextField nameField;
    private final TextField surnameField;
    private final TextField patronymicField;
    private final DateField birthdayField;

    public PersonForm() {
        ResourceBundle bundle = MainUI.getResourceBundle();

        nameField = new TextField(bundle.getString("NameField"));
        surnameField = new TextField(bundle.getString("SurnameField"));
        patronymicField = new TextField(bundle.getString("PatronymicField"));
        birthdayField = new DateField(bundle.getString("BirthdayField"));
        birthdayField.setLocale(bundle.getLocale());

        getMainBody().addComponents(
                nameField,
                surnameField,
                patronymicField,
                birthdayField);
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getSurnameField() {
        return surnameField;
    }

    public TextField getPatronymicField() {
        return patronymicField;
    }

    public DateField getBirthdayField() {
        return birthdayField;
    }
}
