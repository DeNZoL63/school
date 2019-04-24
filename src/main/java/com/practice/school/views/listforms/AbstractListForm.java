package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.practice.school.entity.BaseEntity;
import com.practice.school.interfaces.ListFormActions;
import com.practice.school.views.elements.LocaleElement;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ResourceBundle;

@SpringView
abstract class AbstractListForm<T extends BaseEntity> extends Composite implements ListFormActions, View {

    private GridLayout form = new GridLayout(2, 3);
    private Label header = new Label();
    private Grid<T> grid;
    private Button editButton;
    private Button deleteButton;

    AbstractListForm(Class<T> beanType) {

        ResourceBundle bundle = MainUI.getResourceBundle();

        createGrid(beanType);
        HorizontalLayout buttonsGroup = addButtons(bundle);
        HorizontalLayout buttonsLocale = LocaleElement.getButtonsGroup();
        buttonsLocale.setSizeUndefined();

        form.addComponent(header, 0, 0);
        form.addComponent(buttonsLocale, 1, 0);
        form.addComponent(buttonsGroup, 0, 1, 1, 1);
        form.addComponent(grid, 0, 2, 1, 2);

        form.setSizeFull();

        initExpandRatio();
    }

    GridLayout getForm() {
        return form;
    }

    void setHeaderTitle(String title) {
        header.setValue(title);
        header.setStyleName(ValoTheme.LABEL_H1);
        header.setSizeUndefined();
    }

    Grid<T> getGrid() {
        return grid;
    }

    private void initExpandRatio() {
        form.setColumnExpandRatio(0, 99);
        form.setColumnExpandRatio(1, 1);
        form.setRowExpandRatio(0, 1);
        form.setRowExpandRatio(1, 1);
        form.setRowExpandRatio(2, 98);
    }

    private void createGrid(Class<T> beanType) {
        grid = new Grid<>(beanType);
        grid.addItemClickListener(event -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                editElementDoubleClick(event.getItem().getId());
            }
        });
        grid.addSelectionListener(event -> {
            boolean flag = event.getFirstSelectedItem().isPresent();
            editButton.setEnabled(flag);
            deleteButton.setEnabled(flag);
        });
        grid.setSizeFull();
    }

    private HorizontalLayout addButtons(ResourceBundle bundle) {
        HorizontalLayout buttonsGroup = new HorizontalLayout();

        Button addButton = new Button(bundle.getString("AddKey"));
        editButton = new Button(bundle.getString("EditKey"));
        deleteButton = new Button(bundle.getString("DeleteKey"));

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
        editButton.setEnabled(false);

        deleteButton.setStyleName(ValoTheme.BUTTON_TINY);
        deleteButton.addClickListener((Button.ClickListener) clickEvent -> deleteElement());
        deleteButton.setClickShortcut(ShortcutAction.KeyCode.DELETE);
        deleteButton.setDescription(bundle.getString("DeleteKeyDescription"));
        deleteButton.setId("deleteButton");
        deleteButton.setEnabled(false);

        buttonsGroup.addComponents(addButton, editButton, deleteButton);
        buttonsGroup.setSizeUndefined();
        return buttonsGroup;
    }
}
