package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Lesson;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;

import java.util.ResourceBundle;

@UIScope
@SpringView(name = "lessons")
public class LessonsView extends AbstractListForm {

    public LessonsView() {
        super(Lesson.class);
        final GridLayout form = getForm();

        ResourceBundle bundle = MainUI.getResourceBundle();
        String headerText = bundle.getString("LessonsHeaderText");
        setHeaderTitle(headerText);

        UI.getCurrent().getPage().setTitle(headerText);
        setCompositionRoot(form);
    }

    @Override
    public void addElement() {
        UI.getCurrent().getNavigator().navigateTo("lesson");
        Page.getCurrent().reload();
    }

    @Override
    public void editElement() {
        UI.getCurrent().getNavigator().navigateTo("lesson");
        Page.getCurrent().reload();
    }

    @Override
    public void deleteElement() {

    }

    @Override
    public void editElementDoubleClick(Long id) {

    }
}
