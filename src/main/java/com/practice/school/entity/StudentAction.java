package com.practice.school.entity;

import javax.persistence.Column;
import java.time.LocalDate;

public abstract class StudentAction extends BaseEntity {

    @Column
    private LocalDate date;
    @Column
    private Student student;

    StudentAction() {
    }

    StudentAction(Long id) {
        super(id);
    }

    StudentAction(Long id, Student student, LocalDate date) {
        super(id);
        this.student = student;
        this.date = date;
    }

    StudentAction(Student student, LocalDate date) {
        this.student = student;
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
