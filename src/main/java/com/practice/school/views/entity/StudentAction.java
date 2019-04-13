package com.practice.school.views.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public abstract class StudentAction {

    @Column(unique = true)
    private long id;

    @Column
    private Student student;

    @Column
    private Date date;

    public StudentAction() {
    }

    public StudentAction(long id, Student student, Date date) {
        this.id = id;
        this.student = student;
        this.date = date;
    }

    public StudentAction(Student student, Date date) {
        this.student = student;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
