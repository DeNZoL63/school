package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.ResourceBundle;

public class ExamsView extends AbstractListForm {

    public ExamsView() {
        super();
        VerticalLayout form = getForm();

        ResourceBundle bundle = MainUI.getResourceBundle();
        String headerText = bundle.getString("ExamsHeaderText");
        setHeaderTitle(headerText);

        UI.getCurrent().getPage().setTitle(headerText);
        setCompositionRoot(form);
    }

    @Override
    public void addElement() {

    }

    @Override
    public void editElement() {

    }

    @Override
    public void deleteElement() {

    }
}
