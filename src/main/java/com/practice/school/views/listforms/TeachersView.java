package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.ResourceBundle;

@UIScope
@SpringView(name = "teachers")
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
        Page.getCurrent().reload();
//        new TeacherView();
    }

    @Override
    public void editElement() {
        UI.getCurrent().getNavigator().navigateTo("teacher");
        Page.getCurrent().reload();
//        new TeacherView();
    }

    @Override
    public void deleteElement() {

    }

    @Override
    public void editElementDoubleClick(Long id) {

    }
}
