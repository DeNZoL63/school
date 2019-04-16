package com.practice.school.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "Exams")
public class Exam extends StudentAction{

    @Column
    private ExamKindEnum examKind;

    @Column
    private String detail;

    @Column(length = 1)
    //#TODO ограничить оценку
    private Byte mark;

    public Exam() {

    }

    public Exam(Long id) {
        super(id);
    }

    public Exam(long id, Student student, Date date, ExamKindEnum examKind, String detail, Byte mark) {
        super(id, student, date);
        this.examKind = examKind;
        this.detail = detail;
        this.mark = mark;
    }

    public Exam(Student student, Date date, ExamKindEnum examKind, String detail, Byte mark) {
        super(student, date);
        this.examKind = examKind;
        this.detail = detail;
        this.mark = mark;
    }

    public ExamKindEnum getExamKind() {
        return examKind;
    }

    public void setExamKind(ExamKindEnum examKind) {
        this.examKind = examKind;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Byte getMark() {
        return mark;
    }

    public void setMark(Byte mark) {
        this.mark = mark;
    }
}


