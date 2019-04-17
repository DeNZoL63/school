package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Student;
import com.practice.school.service.impl.StudentServiceImpl;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

@UIScope
@SpringView(name = "student")
public class StudentView extends PersonForm {

    @Autowired
    private StudentServiceImpl studentService;
    private ResourceBundle bundle;
    private TextField idField = getIdField();
    private TextField nameField = getNameField();
    private TextField surnameField = getSurnameField();
    private TextField patronymicField = getPatronymicField();
    private DateField birthdayField = getBirthdayField();
    private TextField phoneField;
    private TextField photoField;
    private CheckBox haveLicenseField;
    private Binder<Student> binder = new Binder<>(Student.class);
    private Student student = new Student();


    public StudentView() {
        customForm();
        addPersonalElements();

        binder.forField(idField)
                .withConverter(new StringToLongConverter(bundle.getString("EnterNumberError")))
                .bind(Student::getId, Student::setId);
        binder.bind(surnameField, Student::getSurname, Student::setSurname);
        binder.bind(nameField, Student::getName, Student::setName);
        binder.bind(patronymicField, Student::getPatronymic, Student::setPatronymic);
        binder.bind(birthdayField, Student::getBirthday, Student::setBirthday);
        binder.bind(phoneField, Student::getPhone, Student::setPhone);
        binder.bind(photoField, Student::getPhoto, Student::setPhoto);
        binder.bind(haveLicenseField, Student::getHaveLicense, Student::setHaveLicense);

        binder.bindInstanceFields(student);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String parameterID = event.getParameterMap().get("id");

        if (parameterID == null || parameterID.equals("0")){
            return;
        }

        this.student = studentService.findById(Long.valueOf(parameterID));

        if (this.student == null){
            return;
        }
        binder.setBean(this.student);
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
    }

    @Override
    public void cancelAction() {
        closeForm();
    }

    private void createStudent() {
        try {
            binder.writeBean(student);
            Student saved = studentService.addStudent(student);
            idField.setValue(saved.getId().toString());
        } catch (ValidationException e) {
            e.printStackTrace();
            Notification.show(e.getMessage());
        }
    }

    private void validateAll() {

    }

    private void addPersonalElements() {
        FormLayout formLayout = getMainBody();

        phoneField = new TextField(bundle.getString("PhoneField"));
        photoField = new TextField(bundle.getString("PhotoField"));
        haveLicenseField = new CheckBox(bundle.getString("HasLicenseField"));

        formLayout.addComponents(
                phoneField,
                photoField,
                haveLicenseField);
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
