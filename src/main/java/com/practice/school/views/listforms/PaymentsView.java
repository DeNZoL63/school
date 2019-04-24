package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Payment;
import com.practice.school.service.impl.PaymentServiceImpl;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@UIScope
@SpringView(name = "payments")
public class PaymentsView extends AbstractListForm {

    @Autowired
    private PaymentServiceImpl paymentService;

    private Grid grid = getGrid();
    private final ResourceBundle bundle = MainUI.getResourceBundle();

    public PaymentsView(PaymentServiceImpl paymentService) {
        super(Payment.class);
        this.paymentService = paymentService;
        final GridLayout form = getForm();

        String headerText = bundle.getString("PaymentsHeaderText");
        setHeaderTitle(headerText);

        setContentGrid();

        Page.getCurrent().setTitle(headerText);
        setCompositionRoot(form);
    }

    @Override
    public void addElement() {
        UI.getCurrent().getNavigator().navigateTo("payment");
    }

    @Override
    public void editElement() {
        final Optional<Payment> item = grid.getSelectionModel().getFirstSelectedItem();

        item.ifPresent(payment -> UI.getCurrent().getNavigator().navigateTo("payment/id=" + payment.getId()));
    }

    @Override
    public void deleteElement() {
        ConfirmDialog.show(UI.getCurrent(),
                bundle.getString("DeleteCaptionConfirmDialog"),
                bundle.getString("DeleteMessageConfirmDialog"),
                bundle.getString("DeleteOkButtonConfirmDialog"),
                bundle.getString("DeleteCancelButtonConfirmDialog"),
                (ConfirmDialog.Listener) dialog -> {
                    if (dialog.isConfirmed()) {
                        removeItem();
                    }
                });
    }

    @Override
    public void editElementDoubleClick(Long id) {
        UI.getCurrent().getNavigator().navigateTo("payment/id=" + id);
    }

    public void setContentGrid() {
        List<Payment> list = paymentService.getAll();
        grid.setItems(list);
        grid.setColumnOrder("id",
                "date",
                "student",
                "amount"
        );
    }

    private void removeItem() {
        final Optional<Payment> item = grid.getSelectionModel().getFirstSelectedItem();

        item.ifPresent(payment -> {
            paymentService.deleteByID(payment.getId());
            setContentGrid();
        });
    }
}
