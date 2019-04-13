package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.ResourceBundle;

public class LessonsView extends AbstractListForm {

    public LessonsView() {
        super();
        VerticalLayout form = getForm();

        ResourceBundle bundle = MainUI.getResourceBundle();
        String headerText = bundle.getString("LessonsHeaderText");
        setHeaderTitle(headerText);

        UI.getCurrent().getPage().setTitle(headerText);
        setCompositionRoot(form);
    }

    @Override
    public void addElement() {
        UI.getCurrent().getNavigator().navigateTo("lesson");
    }

    @Override
    public void editElement() {
        UI.getCurrent().getNavigator().navigateTo("lesson");
    }

    @Override
    public void deleteElement() {

    }
}
