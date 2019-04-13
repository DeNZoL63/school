package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.practice.school.interfaces.ListFormActions;
import com.practice.school.views.elements.LocaleElement;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ResourceBundle;

@SpringView
abstract class AbstractListForm extends Composite implements ListFormActions, View {

    private final VerticalLayout form = new VerticalLayout();
    private final Label header = new Label();

    AbstractListForm() {

        HorizontalLayout buttonsGroup = new HorizontalLayout();

        ResourceBundle bundle = MainUI.getResourceBundle();

        Button addButton = new Button(bundle.getString("AddKey"));
        Button editButton = new Button(bundle.getString("EditKey"));
        Button deleteButton = new Button(bundle.getString("DeleteKey"));

        addButton.setStyleName(ValoTheme.BUTTON_TINY);
        addButton.addClickListener((Button.ClickListener) clickEvent -> addElement());
        addButton.setClickShortcut(ShortcutAction.KeyCode.INSERT);
        addButton.setDescription(bundle.getString("AddKeyDescription"));
        addButton.setId("addButton");

        editButton.setStyleName(ValoTheme.BUTTON_TINY);
        editButton.addClickListener((Button.ClickListener) clickEvent -> editElement());
        editButton.setClickShortcut(ShortcutAction.KeyCode.F2);
        editButton.setDescription(bundle.getString("EditKeyDescription"));
        editButton.setId("editButton");

        deleteButton.setStyleName(ValoTheme.BUTTON_TINY);
        deleteButton.addClickListener((Button.ClickListener) clickEvent -> editElement());
        deleteButton.setClickShortcut(ShortcutAction.KeyCode.DELETE);
        deleteButton.setDescription(bundle.getString("DeleteKeyDescription"));
        deleteButton.setId("deleteButton");

        buttonsGroup.addComponents(addButton, editButton, deleteButton);
        buttonsGroup.setSizeUndefined();

        Grid<Object> studentsGrid = new Grid<>();

        HorizontalLayout buttonsLocale = LocaleElement.getButtonsGroup();
        form.addComponents(buttonsLocale, header, buttonsGroup, studentsGrid);
        form.setDefaultComponentAlignment(Alignment.TOP_LEFT);
        form.setComponentAlignment(buttonsLocale, Alignment.TOP_RIGHT);

        studentsGrid.setSizeFull();
        form.setSizeFull();

        form.setExpandRatio(buttonsLocale, 4f);
        form.setExpandRatio(header, 12f);
        form.setExpandRatio(buttonsGroup, 4f);
        form.setExpandRatio(studentsGrid, 80f);
    }

    VerticalLayout getForm() {
        return form;
    }

    void setHeaderTitle(String title) {
        header.setValue(title);
        header.setStyleName(ValoTheme.LABEL_H1);
    }
}
