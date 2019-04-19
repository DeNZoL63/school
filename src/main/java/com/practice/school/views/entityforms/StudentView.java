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
import java.io.FileNotFoundException;
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
    private Image photoImage;
    private CheckBox haveLicenseField;
    private Binder<Student> binder = new Binder<>(Student.class);
    private Student student = new Student();
    private String pathPhoto = "src/main/resources/static/db_photos/";
    private File photoNotFoundFile = new File(pathPhoto + "notfound.jpg");
    private File photoFile = new File(pathPhoto + student.getId() + "_student.jpg");


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

        photoImage.setSource(new FileResource(photoNotFoundFile));
        photoImage.setHeight("300px");
        photoImage.setWidth("300px");

        if (parameterID == null || parameterID.equals("0")){
            return;
        }

        this.student = studentService.findById(Long.valueOf(parameterID));

        if (this.student == null){
            return;
        }
        binder.setBean(this.student);

        photoFile = new File(student.getPhoto());

        if (photoFile.exists()) {
//            photoFile = new File(pathPhoto + "notfound.jpg");
            photoImage.setSource(new FileResource(photoFile));
        }
//        photoImage.setSource(new FileResource(photoFile));

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
                haveLicenseField);


        final VerticalLayout photoPlusForm = new VerticalLayout();
        photoImage = new Image(bundle.getString("PhotoField"));

        photoPlusForm.addComponent(photoImage);

        Upload upload = createUpload();

        photoPlusForm.addComponent(upload);

        HorizontalLayout mb = getMainBodi();
        mb.addComponent(photoPlusForm);
        mb.setComponentAlignment(photoPlusForm, Alignment.MIDDLE_RIGHT);
        mb.setComponentAlignment(formLayout, Alignment.MIDDLE_LEFT);
        mb.setSizeUndefined();
    }

    private Upload createUpload() {
        Upload upload = new Upload("", (Upload.Receiver) (filename, mimeType) -> {
            // Create upload stream
            OutputStream fos = null; // Stream to write to
            String nameOfFile = "";
            try {
                // Open the file for writing.
                if (student.getId() == null) {
                    //#todo сделать локализацию
                    new Notification("ВНИМАНИЕ",
                            "Сначала сохраните элемент",
                            Notification.Type.WARNING_MESSAGE).show(Page.getCurrent());
                    nameOfFile = pathPhoto + "tmp.jpg";

                    throw new Exception();
//                    return null;
                }else{
                    nameOfFile = pathPhoto + student.getId() + "_student.jpg";
                }
                photoFile = new File(nameOfFile);
                if (photoFile.exists()){
                    photoFile.delete();
                    photoFile = new File(nameOfFile);
                }
                fos = new FileOutputStream(photoFile);
            } catch (final FileNotFoundException e) {
                new Notification("Could not open file<br/>",
                        e.getMessage(),
                        Notification.Type.ERROR_MESSAGE)
                        .show(Page.getCurrent());
//                nameOfFile = pathPhoto + "notfound.jpg";
//                photoFile = new File(nameOfFile);
//                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return fos; // Return the output stream to write to
        });

        upload.addFinishedListener(event -> {
            photoImage.setSource(new FileResource(photoFile));
        });

        upload.addFailedListener(event -> {
//            photoFile = new File(pathPhoto + "notfound.jpg");
            photoImage.setSource(new FileResource(photoNotFoundFile));
            student.setPhoto(photoFile.getPath());
            photoField.setValue(photoFile.getPath());
        });

        upload.addSucceededListener(event -> {
            if (photoFile == null) {
//                photoFile = new File(pathPhoto + "notfound.jpg");
//                photoImage.setSource(new FileResource(photoFile));
                photoImage.setSource(new FileResource(photoNotFoundFile));
            }else {
//                photoImage.setSource(new FileResource(photoFile));
                photoImage.setSource(new FileResource(photoFile));
            }
//            photoImage.setSource(null);
            student.setPhoto(photoFile.getPath());
            photoField.setValue(photoFile.getPath());
        });
        return upload;
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
