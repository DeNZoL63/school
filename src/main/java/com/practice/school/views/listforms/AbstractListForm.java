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
//@Component
abstract class AbstractListForm<T extends BaseEntity> extends Composite implements ListFormActions, View {

    private VerticalLayout form = new VerticalLayout();
    private Label header = new Label();
    private Grid<T> grid;
    private final HorizontalLayout buttonsGroup;
    private final HorizontalLayout buttonsLocale;

    AbstractListForm(Class<T> beanType) {

        ResourceBundle bundle = MainUI.getResourceBundle();

        createGrid(beanType);
        buttonsGroup = addButtons(bundle);
        buttonsLocale = LocaleElement.getButtonsGroup();

        form.addComponents(buttonsLocale, header, buttonsGroup, grid);
        form.setDefaultComponentAlignment(Alignment.TOP_LEFT);
        form.setComponentAlignment(buttonsLocale, Alignment.TOP_RIGHT);
        form.setSizeFull();

        initExpandRatio();
    }

    VerticalLayout getForm() {
        return form;
    }

    void setHeaderTitle(String title) {
        header.setValue(title);
        header.setStyleName(ValoTheme.LABEL_H1);
    }

    Grid<T> getGrid() {
        return grid;
    }

    private void initExpandRatio() {
        form.setExpandRatio(buttonsLocale, 4f);
        form.setExpandRatio(header, 12f);
        form.setExpandRatio(buttonsGroup, 4f);
        form.setExpandRatio(grid, 80f);
    }

    private void createGrid(Class<T> beanType) {
        grid = new Grid<>(beanType);
        grid.addItemClickListener(event -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                editElementDoubleClick(event.getItem().getId());
            }
        });
        grid.addContextClickListener(event -> {
            Notification.show(event.toString());
        });
        grid.setSizeFull();
    }

    private HorizontalLayout addButtons(ResourceBundle bundle) {
        HorizontalLayout buttonsGroup = new HorizontalLayout();

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
        deleteButton.addClickListener((Button.ClickListener) clickEvent -> deleteElement());
        deleteButton.setClickShortcut(ShortcutAction.KeyCode.DELETE);
        deleteButton.setDescription(bundle.getString("DeleteKeyDescription"));
        deleteButton.setId("deleteButton");

        buttonsGroup.addComponents(addButton, editButton, deleteButton);
        buttonsGroup.setSizeUndefined();
        return buttonsGroup;
    }
}
