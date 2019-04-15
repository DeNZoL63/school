package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Student;
import com.practice.school.service.impl.StudentServiceImpl;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

@SpringComponent
@UIScope
//@Route
public class StudentView extends PersonForm {

    private ResourceBundle bundle;
    private TextField nameField = getNameField();
    private TextField surnameField = getSurnameField();
    private TextField patronymicField = getPatronymicField();
    private DateField birthdayField = getBirthdayField();
    private TextField phoneField;
    private TextField photoField;
    private CheckBox hasLicenseField;

    @Autowired
    //#TODO не может получить репозиторий
    private StudentServiceImpl studentService;

    @Autowired
    public StudentView() {
        customForm();
        fillForm();
    }

    @Override
    public void okAction() {
        validateAll();
        createStudent();
        closeForm();
    }

    @Override
    public void applyAction() {
        closeForm();
    }

    @Override
    public void cancelAction() {
        closeForm();
    }

    private void createStudent() {
        Student student = new Student(
                surnameField.getValue(),
                nameField.getValue(),
                patronymicField.getValue(),
                birthdayField.getValue(),
                phoneField.getValue(),
                photoField.getValue(),
                hasLicenseField.getValue()
        );
//        student.setName(nameField.getValue());
//        student.setSurname(surnameField.getValue());
//        student.setPatronymic(patronymicField.getValue());
//        student.setBirthday(birthdayField.getValue());
//        student.setPhone(phoneField.getValue());
//        student.setPhoto(photoField.getValue());
//        student.setHaveLicense(hasLicenseField.getValue());
//        student.setId(new Long(13213231));

        studentService.addStudent(student);
    }

    private void validateAll() {

    }

    private void fillForm() {
        FormLayout formLayout = getMainBody();

        phoneField = new TextField(bundle.getString("PhoneField"));
        photoField = new TextField(bundle.getString("PhotoField"));
        hasLicenseField = new CheckBox(bundle.getString("HasLicenseField"));

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

    private void closeForm() {
        close();
        MainUI.getCurrent().getNavigator().navigateTo("students");
    }
}
