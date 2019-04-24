package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Student;
import com.practice.school.service.impl.StudentServiceImpl;
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
@SpringView(name = "students")
public class StudentsView extends AbstractListForm<Student> {

    @Autowired
    private final StudentServiceImpl studentService;

    private Grid<Student> grid = getGrid();
    private final ResourceBundle bundle;

    public StudentsView(StudentServiceImpl studentService) {
        super(Student.class);
        this.studentService = studentService;
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
    }

    @Override
    public void editElement() {

        final Optional<Student> item = grid.getSelectionModel().getFirstSelectedItem();

        item.ifPresent(student -> UI.getCurrent().getNavigator().navigateTo("student/id=" + student.getId()));
    }

    @Override
    public void editElementDoubleClick(Long id) {
        UI.getCurrent().getNavigator().navigateTo("student/id=" + id);
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

    public void setContentGrid() {
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
        final Optional<Student> item = grid.getSelectionModel().getFirstSelectedItem();

        if (item.isPresent()) {
            studentService.deleteByID(item.get().getId());
            setContentGrid();
        }
    }
}
