package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Student;
import com.practice.school.service.impl.StudentServiceImpl;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;

@UIScope
@SpringView(name = "student")
public class StudentView extends PersonForm {

    @Autowired
    private StudentServiceImpl studentService;
    private ResourceBundle bundle = MainUI.getResourceBundle();
    private TextField idField = getIdField();
    private TextField nameField = getNameField();
    private TextField surnameField = getSurnameField();
    private TextField patronymicField = getPatronymicField();
    private DateField birthdayField = getBirthdayField();
    private TextField phoneField;
    private TextField photoField;
    private Image photo2Field;
    private CheckBox haveLicenseField;
    private Binder<Student> binder = new Binder<>(Student.class);
    private Student student = new Student();


    public StudentView() {
        customForm();
        addPersonalElements();
        bindFields();

        binder.bindInstanceFields(new Student());
        binder.validate();
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
        binder.validate();
    }

    @Override
    public void okAction() {
        validateAll();
        if (createStudent()){
            closeForm();
        }
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

    private void bindFields() {
        String patternChars = "^[a-zA-Zа-яА-Я]{2,50}$";
        String patternNumbers = "^\\+[0-9]{11,11}$";
        RegexpValidator validatorLetters = new RegexpValidator(bundle.getString("FieldCharsValidationError"), patternChars);
        RegexpValidator validatorNumbers = new RegexpValidator(bundle.getString("FieldNumbersValidationError"), patternNumbers);

        binder.forField(idField)
                .withConverter(new StringToLongConverter(bundle.getString("EnterNumberError")))
                .bind(Student::getId, Student::setId);

        binder.forField(surnameField)
                .withValidator(StudentView::checkLengthMoreTwo,
                        bundle.getString("FieldLengthValidationError"))
                .withValidator(StudentView::checkLengthNotEmpty,
                        bundle.getString("FieldLengthValidationError"))
                .withValidator(validatorLetters)
                .bind(Student::getSurname, Student::setSurname);


        binder.forField(nameField)
                .withValidator(StudentView::checkLengthMoreTwo,
                        bundle.getString("FieldLengthValidationError"))
                .withValidator(StudentView::checkLengthNotEmpty,
                        bundle.getString("FieldLengthValidationError"))
                .withValidator(validatorLetters)
                .bind(Student::getName, Student::setName);


        binder.forField(patronymicField)
                .withValidator(StudentView::checkLengthMoreTwo,
                        bundle.getString("FieldLengthValidationError"))
                .withValidator(StudentView::checkLengthNotEmpty,
                        bundle.getString("FieldLengthValidationError"))
                .withValidator(validatorLetters)
                .bind(Student::getPatronymic, Student::setPatronymic);


        binder.forField(phoneField)
                .withValidator(validatorNumbers)
                .bind(Student::getPhone, Student::setPhone);


        binder.bind(birthdayField, Student::getBirthday, Student::setBirthday);
        binder.bind(photoField, Student::getPhoto, Student::setPhoto);
        binder.bind(haveLicenseField, Student::getHaveLicense, Student::setHaveLicense);
    }

    private static boolean checkLengthMoreTwo(String s) {
        return s.length() > 2;
    }

    private static boolean checkLengthNotEmpty(String s) {
        return s.length() > 0;
    }

    private Boolean createStudent() {
        boolean result = false;
        try {
            if (binder.isValid()) {
                binder.writeBean(this.student);
                Student saved = studentService.addStudent(this.student);
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

    private void addPersonalElements() {
        FormLayout formLayout = getFormLayout();

        phoneField = new TextField(bundle.getString("PhoneField"));
        phoneField.setMaxLength(12);
        photoField = new TextField(bundle.getString("PhotoField"));
        haveLicenseField = new CheckBox(bundle.getString("HasLicenseField"));

        formLayout.addComponents(
                phoneField,
//                photoField,
                haveLicenseField);


        // Image as a file resource
        FileResource resource = new FileResource(new File("db_photos/"+"thousand_words_01.jpg"));
        final VerticalLayout vL = new VerticalLayout();
        photo2Field = new Image(bundle.getString("PhotoField"), resource);
        photo2Field.setHeight("300px");
        photo2Field.setWidth("300px");
        vL.addComponent(photo2Field);



        Upload upload = new Upload("", new Upload.Receiver() {
            @Override
            public OutputStream receiveUpload(String filename, String mimeType) {

                // Create upload stream
                OutputStream fos = null; // Stream to write to
                try {
                    // Open the file for writing.
                    File file = new File("db_photos/"+ filename);
                    fos = new FileOutputStream(file);
                } catch (final java.io.FileNotFoundException e) {
                    new Notification("Could not open file<br/>",
                            e.getMessage(),
                            Notification.Type.ERROR_MESSAGE)
                            .show(Page.getCurrent());
                    return null;
                }
                return fos; // Return the output stream to write to
            }
        });
//        upload.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif");

        upload.addSucceededListener(event -> {
//            Component component = createComponent(event.getMIMEType(),
//                    event.getFilename(),
//                    buffer.getInputStream(event.getFilename()));
            photo2Field.setSource(new FileResource(new File("db_photos/" + event.getFilename())));
        });

        vL.addComponent(upload);



        HorizontalLayout mb = getMainBodi();
//        mb.addComponentAsFirst(photo2Field);
        mb.addComponent(vL);
//        mb.setComponentAlignment(photo2Field, Alignment.MIDDLE_RIGHT);
        mb.setComponentAlignment(vL, Alignment.MIDDLE_RIGHT);
        mb.setComponentAlignment(formLayout, Alignment.MIDDLE_LEFT);
        mb.setSizeUndefined();
//        formLayout.addComponent(photo2Field);

//        final HorizontalLayout horizontalLayout = new HorizontalLayout();
//        horizontalLayout.addComponents(formLayout, photo2Field);
    }

    private void customForm(){
        UI.getCurrent().getPage().setTitle(bundle.getString("TitleFormStudent"));
        setHeaderTitle(bundle.getString("TitleFormStudent"));
    }

    private void closeForm() {
        close();
        MainUI.getCurrent().getNavigator().navigateTo("students");
    }
}
