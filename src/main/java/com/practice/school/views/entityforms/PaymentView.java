package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Payment;
import com.practice.school.entity.Student;
import com.practice.school.service.impl.PaymentServiceImpl;
import com.practice.school.service.impl.StudentServiceImpl;
import com.practice.school.views.listforms.PaymentsView;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

@SpringView(name = "payment")
public class PaymentView extends StudentActionForm {

    @Autowired
    private PaymentServiceImpl paymentService;
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private PaymentsView paymentsView;

    private Payment payment = new Payment();
    private Binder<Payment> binder = new Binder<>();
    private TextField idField = getIdField();
    private ComboBox<Student> studentField = getStudentField();
    private DateField dateField = getDateField();
    private TextField amountField;
    private ResourceBundle bundle = MainUI.getResourceBundle();

    public PaymentView(){
        customForm();
        fillForm();
        bindFields();

        binder.bindInstanceFields(new Payment());
        binder.validate();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String parameterID = event.getParameterMap().get("id");
        studentField.setItems(studentService.getAll());

        if (parameterID == null || parameterID.equals("0")){
            return;
        }

        this.payment = paymentService.findById(Long.valueOf(parameterID));

        if (this.payment == null){
            return;
        }

        binder.setBean(this.payment);
        binder.validate();
    }

    @Override
    public void okAction() {
        validateAll();
        if (createPayment()){
            closeForm();
        }
    }

    @Override
    public void applyAction() {
        validateAll();
        createPayment();
    }

    @Override
    public void cancelAction() {
        closeForm();
    }

    @Override
    public Component getViewComponent() {
        return paymentsView;
    }

    private void customForm() {
        UI.getCurrent().getPage().setTitle(bundle.getString("TitleFormPayment"));
        setHeaderTitle(bundle.getString("TitleFormPayment"));
    }

    private void fillForm() {
        FormLayout formLayout = getFormLayout();
        amountField = new TextField(bundle.getString("AmountField"));

        formLayout.addComponents(
                amountField);

    }

    private boolean createPayment() {
        boolean result = false;
        try {
            if (binder.isValid()) {
                binder.writeBean(this.payment);
                Payment saved = paymentService.addPayment(this.payment);
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

    private void closeForm() {
        close();
        paymentsView.setContentGrid();
        MainUI.getCurrent().getNavigator().navigateTo("payments");
    }

    private void bindFields() {
        String patternChars = "^[a-zA-Zа-яА-Я]{2,50}$";
        String patternNumbersAmount = "^[0-9]{1,18}\\.[0-9]{0,2}$";
        RegexpValidator validatorLetters = new RegexpValidator(bundle.getString("FieldCharsValidationError"), patternChars);
        RegexpValidator validatorNumbersAmount = new RegexpValidator(bundle.getString("FieldNumbersValidationError"), patternNumbersAmount);

        binder.forField(idField)
                .withConverter(new StringToLongConverter(bundle.getString("EnterNumberError")))
                .bind(Payment::getId, Payment::setId);

        binder.forField(studentField)
                .bind(Payment::getStudent, Payment::setStudent);


        binder.forField(dateField)
                .bind(Payment::getDate, Payment::setDate);

        binder.forField(amountField)
                .withConverter(new StringToDoubleConverter(bundle.getString("EnterNumberError")))
                .bind(Payment::getAmount, Payment::setAmount);

    }

    private static boolean checkLengthMoreTwo(String s) {
        return s.length() > 2;
    }

    private static boolean checkLengthNotEmpty(String s) {
        return s.length() > 0;
    }
}
