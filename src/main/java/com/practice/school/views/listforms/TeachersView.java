package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Teacher;
import com.practice.school.service.impl.TeacherServiceImpl;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@UIScope
@SpringView(name = "teachers")
public class TeachersView extends AbstractListForm {

    @Autowired
    private final TeacherServiceImpl teacherService;
    private Grid<Teacher> grid = getGrid();
    private final ResourceBundle bundle;

    public TeachersView(TeacherServiceImpl teacherService) {
        super(Teacher.class);
        final GridLayout form = getForm();
        this.teacherService = teacherService;
        bundle = MainUI.getResourceBundle();

        ResourceBundle bundle = MainUI.getResourceBundle();
        String headerText = bundle.getString("TeachersHeaderText");
        setHeaderTitle(headerText);

        setContentGrid();

        Page.getCurrent().setTitle(headerText);
        setCompositionRoot(form);
    }


    @Override
    public void addElement() {
        UI.getCurrent().getNavigator().navigateTo("teacher");
    }

    @Override
    public void editElement() {
        final Optional<Teacher> item = grid.getSelectionModel().getFirstSelectedItem();

        item.ifPresent(teacher -> UI.getCurrent().getNavigator().navigateTo("teacher/id=" + teacher.getId()));
    }

    @Override
    public void deleteElement() {
        ConfirmDialog.show(UI.getCurrent(),
                bundle.getString("DeleteCaptionConfirmDialog"),
                bundle.getString("DeleteMessageConfirmDialog"),
                bundle.getString("DeleteOkButtonConfirmDialog"),
                bundle.getString("DeleteCancelButtonConfirmDialog"),
                (ConfirmDialog.Listener) dialog -> {
                    if (dialog.isConfirmed()) {
                        removeItem();
                    }
                });
    }

    @Override
    public void editElementDoubleClick(Long id) {
        UI.getCurrent().getNavigator().navigateTo("teacher/id=" + id);
    }

    private void removeItem() {
        final Optional<Teacher> item = grid.getSelectionModel().getFirstSelectedItem();

        item.ifPresent(teacher -> {
            teacherService.deleteByID(teacher.getId());
            setContentGrid();
        });
    }

    public void setContentGrid() {
        List<Teacher> list = teacherService.getAll();
        grid.setItems(list);
        grid.setColumnOrder("id",
                "surname",
                "name",
                "patronymic",
                "birthday",
                "licenseNumber",
                "experience"
        );
    }


}
