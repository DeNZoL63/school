package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.practice.school.dao.DBProvider;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StudentView extends PersonForm {

    private ResourceBundle bundle;

    @Autowired
    public StudentView() {
        customForm();
        fillForm();
    }

    private void fillForm() {
        FormLayout formLayout = getMainBody();

        final TextField phoneField = new TextField(bundle.getString("PhoneField"));
        final TextField photoField = new TextField(bundle.getString("PhotoField"));
        final CheckBox hasLicenseField = new CheckBox(bundle.getString("HasLicenseField"));

        formLayout.addComponents(
                phoneField,
                photoField,
                hasLicenseField);
    }

    private void customForm(){
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

        test();
        closeForm();
    }

    private void test() {
        ResultSet rs = null;
        boolean flag = false;

        try(Connection connection = DBProvider.connectToDB();
            Statement statement = connection.createStatement()) {

            rs = statement.executeQuery("");
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
