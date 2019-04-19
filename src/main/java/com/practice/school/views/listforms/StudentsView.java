package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Student;
import com.practice.school.interfaces.CommonMethods;
import com.practice.school.service.impl.StudentServiceImpl;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.dialogs.ConfirmDialog;

import java.util.List;
import java.util.ResourceBundle;

@UIScope
@SpringView(name = "students")
public class StudentsView extends AbstractListForm<Student> {

    @Autowired
    private final StudentServiceImpl studentService;
    private Grid<Student> grid = getGrid();
    private final ResourceBundle bundle;

    public StudentsView(StudentServiceImpl studentService) {
        super(Student.class);
        this.studentService = studentService;
//        VerticalLayout form = getForm();
        final GridLayout form = getForm();

        bundle = MainUI.getResourceBundle();
        String headerText = bundle.getString("StudentsHeaderText");
        setHeaderTitle(headerText);

        setContentGrid();

        UI.getCurrent().getPage().setTitle(headerText);
        setCompositionRoot(form);
    }

    @Override
    public void addElement() {
        UI.getCurrent().getNavigator().navigateTo("student");
        Page.getCurrent().reload();
    }

    @Override
    public void editElement() {
        Long id = CommonMethods.getSelectedtRow(grid.getSelectedItems());

        if (id == null || id.equals(0L)) {
            return;
        }
        UI.getCurrent().getNavigator().navigateTo("student/id=" + id);
        Page.getCurrent().reload();
    }

    @Override
    public void editElementDoubleClick(Long id) {
        UI.getCurrent().getNavigator().navigateTo("student/id=" + id);
        Page.getCurrent().reload();
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

    private void setContentGrid() {
        List<Student> list = studentService.getAll();
        grid.setItems(list);
        grid.setColumnOrder("id",
                "surname",
                "name",
                "patronymic",
                "birthday",
                "phone",
                "photo",
                "haveLicense");
    }

    private void removeItem() {
        Long id = CommonMethods.getSelectedtRow(grid.getSelectedItems());

        if (id == null) {
            return;
        }
        studentService.deleteByID(id);
        setContentGrid();
    }
}
