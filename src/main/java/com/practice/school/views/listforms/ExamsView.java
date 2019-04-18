package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Exam;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.ResourceBundle;

@UIScope
@SpringView(name = "exams")
public class ExamsView extends AbstractListForm {

    public ExamsView() {
        super(Exam.class);
        VerticalLayout form = getForm();

        ResourceBundle bundle = MainUI.getResourceBundle();
        String headerText = bundle.getString("ExamsHeaderText");
        setHeaderTitle(headerText);

        UI.getCurrent().getPage().setTitle(headerText);
        setCompositionRoot(form);
    }

    @Override
    public void addElement() {
        UI.getCurrent().getNavigator().navigateTo("exam");
        Page.getCurrent().reload();
//        new ExamView();
    }

    @Override
    public void editElement() {
        UI.getCurrent().getNavigator().navigateTo("exam");
        Page.getCurrent().reload();
//        new ExamView();
    }

    @Override
    public void deleteElement() {

    }

    @Override
    public void editElementDoubleClick(Long id) {

    }
}
