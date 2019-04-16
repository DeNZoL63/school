package com.practice.school.entity;

import javax.persistence.Column;
import java.util.Date;

public abstract class StudentAction extends BaseEntity {

    @Column
    private Student student;

    @Column
    private Date date;

    StudentAction() {
    }

    public StudentAction(Long id) {
        super(id);
    }

    public StudentAction(Long id, Student student, Date date) {
        super(id);
        this.student = student;
        this.date = date;
    }

    StudentAction(Student student, Date date) {
        this.student = student;
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
