package com.practice.school.views.entityforms;

import com.practice.school.MainUI;
import com.practice.school.views.entity.ExamKindEnum;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ResourceBundle;

public class ExamView extends StudentActionForm {

    private ResourceBundle bundle;

    @Autowired
    public ExamView(){
        customForm();
        fillForm();
    }

    private void customForm() {
        bundle = MainUI.getResourceBundle();
        UI.getCurrent().getPage().setTitle(bundle.getString("TitleFormExam"));
        setHeaderTitle(bundle.getString("TitleFormExam"));
    }

    private void fillForm() {
        FormLayout formLayout = getMainBody();

        final ComboBox<ExamKindEnum> examKindField = new ComboBox<>(bundle.getString("ExamKindField"));
        final RichTextArea detailField = new RichTextArea(bundle.getString("DetailField"));
        final ComboBox<Byte> markField = new ComboBox<>(bundle.getString("markField"));

        formLayout.addComponents(
                examKindField,
                detailField,
                markField);
    }

    @Override
    public void applyAction() {
        closeForm();
    }

    @Override
    public void okAction() {
        closeForm();
    }

    @Override
    public void cancelAction() {
        closeForm();
    }

    private void closeForm() {
        close();
        MainUI.getCurrent().getNavigator().navigateTo("exams");
    }
}
