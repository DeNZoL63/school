package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.ResourceBundle;

public class TeachersView extends AbstractListForm {

    public TeachersView() {
        super();
        VerticalLayout form = getForm();

        ResourceBundle bundle = MainUI.getResourceBundle();
        String headerText = bundle.getString("TeachersHeaderText");
        setHeaderTitle(headerText);

        Page.getCurrent().setTitle(headerText);
        setCompositionRoot(form);
    }


    @Override
    public void addElement() {
        UI.getCurrent().getNavigator().navigateTo("teacher");
    }

    @Override
    public void editElement() {
        UI.getCurrent().getNavigator().navigateTo("teacher");
    }

    @Override
    public void deleteElement() {

    }
}
