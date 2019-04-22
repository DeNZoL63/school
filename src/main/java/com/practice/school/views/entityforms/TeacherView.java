package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Teacher;
import com.practice.school.service.impl.TeacherServiceImpl;
import com.practice.school.views.listforms.TeachersView;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

//@UIScope
@SpringView(name = "teacher")
public class TeacherView extends PersonForm {

    @Autowired
    private TeacherServiceImpl teacherService;
    @Autowired
    private TeachersView teachersView;
    private ResourceBundle bundle;
    private Teacher teacher = new Teacher();
    private Binder<Teacher> binder = new Binder<>(Teacher.class);
    private TextField idField = getIdField();
    private TextField surnameField = getSurnameField();
    private TextField nameField = getNameField();
    private TextField patronymicField = getPatronymicField();
    private DateField birthdayField = getBirthdayField();
    private TextField experienceField;
    private TextField licenseNumberField;

    @Override
    public Component getViewComponent() {
        return teachersView;
    }

    public TeacherView() {
        customForm();
        fillForm();
        bindFields();

        binder.bindInstanceFields(new Teacher());
        binder.validate();
    }

    private void fillForm() {
        FormLayout formLayout = getFormLayout();

        experienceField = new TextField(bundle.getString("ExperienceField"));
        licenseNumberField = new TextField(bundle.getString("LicenseNumberField"));

        formLayout.addComponents(
                experienceField,
                licenseNumberField);
    }

    private void customForm(){
        bundle = MainUI.getResourceBundle();
        UI.getCurrent().getPage().setTitle(bundle.getString("TitleFormTeacher"));
        setHeaderTitle(bundle.getString("TitleFormTeacher"));
    }

    @Override
    public void applyAction() {
        validateAll();
        createTeacher();
    }

    @Override
    public void okAction() {
        validateAll();
        if (createTeacher()){
            closeForm();
        }
    }

    private void bindFields() {
        String patternChars = "^[a-zA-Zа-яА-Я]{2,50}$";
        String patternNumbersLicense = "^[0-9]{6,6}$";
        String patternNumbersExpirience = "^[0-9]{1,2}$";
        RegexpValidator validatorLetters = new RegexpValidator(bundle.getString("FieldCharsValidationError"), patternChars);
        RegexpValidator validatorNumbersLicense = new RegexpValidator(bundle.getString("FieldNumbersValidationError"), patternNumbersLicense);
        RegexpValidator validatorNumbersExpirience = new RegexpValidator(bundle.getString("FieldNumbersValidationError"), patternNumbersExpirience);

        binder.forField(idField)
                .withConverter(new StringToLongConverter(bundle.getString("EnterNumberError")))
                .bind(Teacher::getId, Teacher::setId);

        binder.forField(surnameField)
                .withValidator(TeacherView::checkLengthMoreTwo,
                        bundle.getString("FieldLengthValidationError"))
                .withValidator(TeacherView::checkLengthNotEmpty,
                        bundle.getString("FieldLengthValidationError"))
                .withValidator(validatorLetters)
                .bind(Teacher::getSurname, Teacher::setSurname);


        binder.forField(nameField)
                .withValidator(TeacherView::checkLengthMoreTwo,
                        bundle.getString("FieldLengthValidationError"))
                .withValidator(TeacherView::checkLengthNotEmpty,
                        bundle.getString("FieldLengthValidationError"))
                .withValidator(validatorLetters)
                .bind(Teacher::getName, Teacher::setName);

        binder.forField(patronymicField)
                .withValidator(TeacherView::checkLengthMoreTwo,
                        bundle.getString("FieldLengthValidationError"))
                .withValidator(TeacherView::checkLengthNotEmpty,
                        bundle.getString("FieldLengthValidationError"))
                .withValidator(validatorLetters)
                .bind(Teacher::getPatronymic, Teacher::setPatronymic);

        binder.forField(licenseNumberField)
                .withValidator(validatorNumbersLicense)
                .bind(Teacher::getLicenseNumber, Teacher::setLicenseNumber);

        binder.forField(experienceField)
                .withValidator(validatorNumbersExpirience)
                .bind(Teacher::getExperience, Teacher::setExperience);

        binder.bind(birthdayField, Teacher::getBirthday, Teacher::setBirthday);
    }

    private boolean createTeacher() {
        boolean result = false;
        try {
            if (binder.isValid()) {
                binder.writeBean(this.teacher);
                Teacher saved = teacherService.addTeacher(this.teacher);
                idField.setValue(saved.getId().toString());
                result = true;
            }else{
                Notification.show(bundle.getString("FillCorrectDataMessage"),"", Notification.Type.HUMANIZED_MESSAGE);
            }
        } catch (ValidationException e) {
            e.printStackTrace();
            Notification.show(e.getMessage());
        }
        return result;
    }

    private void validateAll() {
    }

    @Override
    public void cancelAction() {
        closeForm();
    }

    private void closeForm() {
        close();
        teachersView.setContentGrid();
        MainUI.getCurrent().getNavigator().navigateTo("teachers");
    }

    private static boolean checkLengthMoreTwo(String s) {
        return s.length() > 2;
    }

    private static boolean checkLengthNotEmpty(String s) {
        return s.length() > 0;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String parameterID = event.getParameterMap().get("id");

        if (parameterID == null || parameterID.equals("0")){
            return;
        }

        this.teacher = teacherService.findById(Long.valueOf(parameterID));

        if (this.teacher == null){
            return;
        }

        binder.setBean(this.teacher);
        binder.validate();
    }
}
