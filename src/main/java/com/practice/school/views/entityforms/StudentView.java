package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Student;
import com.practice.school.service.impl.StudentServiceImpl;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

@UIScope
@SpringView(name = "student")
public class StudentView extends PersonForm {

    private ResourceBundle bundle;
    private TextField idField = getIdField();
    private TextField nameField = getNameField();
    private TextField surnameField = getSurnameField();
    private TextField patronymicField = getPatronymicField();
    private DateField birthdayField = getBirthdayField();
    private TextField phoneField;
    private TextField photoField;
    private CheckBox hasLicenseField;

    @Autowired
    private StudentServiceImpl studentService;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String parameterID = event.getParameterMap().get("id");
        if (parameterID.isEmpty()){
            return;
        }

        Student student = studentService.findById(Long.valueOf(parameterID));

        if (student == null){
            return;
        }

        surnameField.setValue(student.getSurname());
        nameField.setValue(student.getName());
        patronymicField.setValue(student.getPatronymic());
        birthdayField.setValue(student.getBirthday());
        phoneField.setValue(student.getPhone());
        photoField.setValue(student.getPhoto());
        hasLicenseField.setValue(student.isHaveLicense());
        idField.setValue(student.getId().toString());
    }

    public StudentView() {
        customForm();
        addPersonalElements();
    }

    @Override
    public void okAction() {
        validateAll();
        createStudent();
        closeForm();
    }

    @Override
    public void applyAction() {
        validateAll();
        createStudent();
//        closeForm();
    }

    @Override
    public void cancelAction() {
        closeForm();
    }

    private void createStudent() {
        Student student = new Student();
        fillStudent(student);

        if (!idField.isEmpty()){
            student.setId(Long.valueOf(idField.getValue()));
        }

        studentService.addStudent(student);
    }

    private void fillStudent(Student student){
        student.setSurname(surnameField.getValue());
        student.setName(nameField.getValue());
        student.setPatronymic(patronymicField.getValue());
        student.setBirthday(birthdayField.getValue());
        student.setPhone(phoneField.getValue());
        student.setPhoto(photoField.getValue());
        student.setHaveLicense(hasLicenseField.getValue());
    }

    private void validateAll() {

    }

    private void addPersonalElements() {
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
