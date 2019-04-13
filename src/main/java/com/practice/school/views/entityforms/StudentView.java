package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

public class StudentView extends PersonForm {

    private ResourceBundle bundle;

    @Autowired
    public StudentView() {
        super();
        VerticalLayout form = getForm();
        customForm();
        fillForm();
        setContent(form);
    }

    private void fillForm() {
        FormLayout formLayout = getMainBody();

        final TextField phoneField = new TextField(bundle.getString("PhoneField"));

//        com.vaadin.flow.component.textfield.TextField textField = new com.vaadin.flow.component.textfield.TextField();
//        new PhoneFieldFormatter( "LU").extend(textField);

        final TextField photoField = new TextField(bundle.getString("PhotoField"));
        final CheckBox hasLicenseField = new CheckBox(bundle.getString("HasLicenseField"));

        formLayout.addComponents(
                phoneField,
                photoField,
                hasLicenseField);
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
        UI.getCurrent().getPage().setTitle(bundle.getString("TitleFormStudent"));
        setHeaderTitle(bundle.getString("TitleFormStudent"));
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
        MainUI.getCurrent().getNavigator().navigateTo("students");
    }
}
