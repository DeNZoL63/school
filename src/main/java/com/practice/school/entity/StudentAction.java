package com.practice.school.entity;

import javax.persistence.Column;
import java.util.Date;

public abstract class StudentAction {

    @Column
    private Student student;

    @Column
    private Date date;

    StudentAction() {
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
