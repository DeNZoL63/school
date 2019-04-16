package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.practice.school.views.entityforms.StudentView;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.ResourceBundle;

@UIScope
@SpringView(name = "students")
public class StudentsView extends AbstractListForm {

    public StudentsView() {
//        super();
        VerticalLayout form = getForm();

        ResourceBundle bundle = MainUI.getResourceBundle();
        String headerText = bundle.getString("StudentsHeaderText");
        setHeaderTitle(headerText);

        UI.getCurrent().getPage().setTitle(headerText);
        setCompositionRoot(form);
    }


    @Override
    public void addElement() {
//        UI.getCurrent().getNavigator().navigateTo("student");
        new StudentView();
    }

    @Override
    public void editElement() {
//        UI.getCurrent().getNavigator().navigateTo("student");
//        Page.getCurrent().reload();
        new StudentView();
    }

    @Override
    public void deleteElement() {

    }
}
