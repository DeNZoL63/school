package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

public class PaymentView extends StudentActionForm {

    private ResourceBundle bundle;

    @Autowired
    public PaymentView(){
        customForm();
        fillForm();
    }

    private void customForm() {
        bundle = MainUI.getResourceBundle();
        UI.getCurrent().getPage().setTitle(bundle.getString("TitleFormPayment"));
        setHeaderTitle(bundle.getString("TitleFormPayment"));
    }

    private void fillForm() {
        FormLayout formLayout = getMainBody();

        final TextField amountField = new TextField(bundle.getString("AmountField"));

        formLayout.addComponents(
                amountField);
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
        MainUI.getCurrent().getNavigator().navigateTo("payments");
    }
}
