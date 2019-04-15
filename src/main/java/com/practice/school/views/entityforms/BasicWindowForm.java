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

//@SpringView
//@SpringComponent
abstract class BasicWindowForm extends Window implements View, OkCancelActions, ApplyAction {

    private VerticalLayout form = new VerticalLayout();
    private Label header = new Label();
    private VerticalLayout mainBody;
    private final HorizontalLayout buttonsLocale;
    private final HorizontalLayout mainButtonsGroup;
    private FormLayout formLayout;

    BasicWindowForm() {
        setWindowMode(WindowMode.MAXIMIZED);
        this.setModal(true);
        setClosable(false);
        setResizable(false);

        UI.getCurrent().addWindow(this);

        mainButtonsGroup = getButtonsGroup();

        fillMainBody();

        buttonsLocale = LocaleElement.getButtonsGroup();

        form.addComponents(
                buttonsLocale,
                header,
                mainBody,
                mainButtonsGroup);

        setFormAligment();
        setRatio();
        setContent(form);
    }

    @Override
    public Component getViewComponent() {
        return new VerticalLayout();
    }

    public VerticalLayout getForm() {
        return form;
    }

    FormLayout getMainBody() {
        return formLayout;
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
        buttonsGroup.setSizeUndefined();
        return buttonsGroup;
    }

    private void fillMainBody(){
        mainBody = new VerticalLayout();

        formLayout = new FormLayout();
        formLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        formLayout.setSizeUndefined();

        mainBody.addComponent(formLayout);
        mainBody.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
    }

    private void setFormAligment() {
        form.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        form.setComponentAlignment(buttonsLocale, Alignment.TOP_RIGHT);
        form.setComponentAlignment(header, Alignment.TOP_CENTER);
        form.setComponentAlignment(mainButtonsGroup, Alignment.TOP_CENTER);
        form.setSizeFull();
    }

    private void setRatio() {
        form.setExpandRatio(buttonsLocale, 4f);
        form.setExpandRatio(header, 12f);
        form.setExpandRatio(mainBody, 80f);
        form.setExpandRatio(mainButtonsGroup, 4f);
    }
}
