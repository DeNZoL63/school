package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.practice.school.interfaces.ApplyAction;
import com.practice.school.interfaces.OkCancelActions;
import com.practice.school.views.elements.LocaleElement;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ResourceBundle;

abstract class BasicWindowForm extends Window implements View, OkCancelActions, ApplyAction {

    private GridLayout form = new GridLayout(2, 4);
    private Label header = new Label();
    private HorizontalLayout mainBody;
    private final HorizontalLayout buttonsLocale;
    private final HorizontalLayout mainButtonsGroup;
    private FormLayout formLayout;
    private TextField idField;

    public BasicWindowForm() {
        setWindowMode(WindowMode.NORMAL);
        this.setModal(true);
        this.setWidth("50%");
        this.setHeight("100%");
        this.setModal(true);
        this.addCloseListener(e -> cancelAction());

        setClosable(true);
        setResizable(true);

        UI.getCurrent().addWindow(this);

        mainButtonsGroup = getButtonsGroup();

        fillMainBody();

        buttonsLocale = LocaleElement.getButtonsGroup();

        form.addComponent(header, 0, 1);
        form.addComponent(buttonsLocale, 1, 1);
        form.addComponent(mainBody, 0, 2, 1, 2);
        form.addComponent(mainButtonsGroup, 0, 3, 1, 3);

        setFormAligment();
        setRatio();
        setContent(form);
    }

    @Override
    public Component getViewComponent() {
        return form;
    }

    public GridLayout getForm() {
        return form;
    }

    FormLayout getFormLayout() {
        return formLayout;
    }

    HorizontalLayout getMainBodi() {
        return mainBody;
    }

    void setHeaderTitle(String title) {
        header.setValue(title);
        header.setStyleName(ValoTheme.LABEL_H1);
    }

    private HorizontalLayout getButtonsGroup() {
        HorizontalLayout buttonsGroup = new HorizontalLayout();

        ResourceBundle bundle = MainUI.getResourceBundle();

        Button okButton = new Button(bundle.getString("OkKey"));
        Button applyButton = new Button(bundle.getString("ApplyKey"));
        Button cancelButton = new Button(bundle.getString("CancelKey"));

        okButton.setStyleName(ValoTheme.BUTTON_TINY);
        okButton.addClickListener(clickEvent -> okAction());
        okButton.setClickShortcut(ShortcutAction.KeyCode.ENTER, ShortcutAction.ModifierKey.CTRL);
        okButton.setDescription(bundle.getString("OkKeyDescription"));
        okButton.setId("okKey");

        applyButton.setStyleName(ValoTheme.BUTTON_TINY);
        applyButton.addClickListener(clickEvent -> applyAction());
        applyButton.setClickShortcut(ShortcutAction.KeyCode.ENTER, ShortcutAction.ModifierKey.ALT);
        applyButton.setDescription(bundle.getString("ApplyKeyDescription"));
        applyButton.setId("applyKey");

        cancelButton.setStyleName(ValoTheme.BUTTON_TINY);
        cancelButton.addClickListener(clickEvent -> cancelAction());
        cancelButton.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        cancelButton.setDescription(bundle.getString("CancelKeyDescription"));
        cancelButton.setId("cancelButton");

        buttonsGroup.addComponents(okButton, applyButton, cancelButton);
        buttonsGroup.setWidthUndefined();
        buttonsGroup.setHeight("100%");
        return buttonsGroup;
    }

    public TextField getIdField() {
        return idField;
    }

    private void fillMainBody(){
        mainBody = new HorizontalLayout();
        mainBody.setWidthUndefined();
        mainBody.setHeight("100%");
        mainBody.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        formLayout = new FormLayout();
        formLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        formLayout.setWidthUndefined();
        formLayout.setHeight("100%");

        idField = new TextField("ID");
        idField.setEnabled(false);
        formLayout.addComponent(idField);

        mainBody.addComponent(formLayout);
        mainBody.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
    }

    private void setFormAligment() {
        form.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        form.setComponentAlignment(buttonsLocale, Alignment.TOP_RIGHT);
        form.setComponentAlignment(header, Alignment.TOP_RIGHT);
        form.setComponentAlignment(mainButtonsGroup, Alignment.TOP_CENTER);
        form.setComponentAlignment(mainBody, Alignment.TOP_CENTER);
        form.setSizeFull();
    }

    private void setRatio() {
        form.setRowExpandRatio(0, 5);
        form.setRowExpandRatio(1, 10);
        form.setRowExpandRatio(2, 75);
        form.setRowExpandRatio(3, 10);

        form.setColumnExpandRatio(0, 50);
        form.setColumnExpandRatio(1, 50);
        form.setComponentAlignment(buttonsLocale, Alignment.BOTTOM_RIGHT);
    }

}
