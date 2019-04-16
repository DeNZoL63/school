package com.practice.school.views.listforms;

import com.practice.school.MainUI;
import com.practice.school.entity.Student;
import com.practice.school.interfaces.CommonMethods;
import com.practice.school.service.impl.StudentServiceImpl;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ResourceBundle;

@UIScope
@SpringView(name = "students")
public class StudentsView extends AbstractListForm {

    @Autowired
    private final StudentServiceImpl studentService;
    private Grid<Student> grid;

    public StudentsView(StudentServiceImpl studentService) {
        super();
        this.studentService = studentService;
        VerticalLayout form = getForm();

        ResourceBundle bundle = MainUI.getResourceBundle();
        String headerText = bundle.getString("StudentsHeaderText");
        setHeaderTitle(headerText);

        greateGrid();
        setContentGrid();
        grid.setColumnOrder("id",
                "surname",
                "name",
                "patronymic",
                "birthday",
                "phone",
                "photo",
                "haveLicense");

        UI.getCurrent().getPage().setTitle(headerText);
        setCompositionRoot(form);
    }

    private void setContentGrid() {
        List<Student> list = studentService.getAll();
        grid.setItems(list);
    }

    private void greateGrid() {
        VerticalLayout gridLayout = getGridLayout();
        grid = new Grid<>(Student.class);
        grid.setSizeFull();
        gridLayout.setSpacing(false);
        gridLayout.addComponent(grid);
    }


    @Override
    public void addElement() {
        UI.getCurrent().getNavigator().navigateTo("student");
        Page.getCurrent().reload();
    }

    @Override
    public void editElement() {
        Long id = CommonMethods.getSelectedtRow(grid.getSelectedItems());

        if (id == null) {
            return;
        }
        UI.getCurrent().getNavigator().navigateTo("student");
        Page.getCurrent().reload();
    }

    @Override
    public void deleteElement() {
        Long id = CommonMethods.getSelectedtRow(grid.getSelectedItems());

        if (id == null) {
            return;
        }
        studentService.deleteByID(id);
        setContentGrid();
    }
}
