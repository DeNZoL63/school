package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.ResourceBundle;

public class StudentsView extends AbstractListForm {

    public StudentsView() {
        super();
        VerticalLayout form = getForm();

        ResourceBundle bundle = MainUI.getResourceBundle();
        String headerText = bundle.getString("StudentsHeaderText");
        setHeaderTitle(headerText);

        UI.getCurrent().getPage().setTitle(headerText);
        setCompositionRoot(form);
    }


    @Override
    public void addElement() {
        UI.getCurrent().getNavigator().navigateTo("student");
    }

    @Override
    public void editElement() {

    }

    @Override
    public void deleteElement() {

    }
}
