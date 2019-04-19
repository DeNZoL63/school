package com.practice.school.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.Time;
import java.util.Date;

@Table(name = "Lesson")
public class Lesson extends StudentAction {

    @Column
    private Time time;

    @Column
    private Teacher teacher;

    @Column
    private String gpsTrack;

    public Lesson() {
    }

    public Lesson(Long id) {
        super(id);
    }

    public Lesson(long id, Student student, Date date, Time time, Teacher teacher, String gpsTrack) {
        super(id, student, date);
        this.time = time;
        this.teacher = teacher;
        this.gpsTrack = gpsTrack;
    }

    public Lesson(Student student, Date date, Time time, Teacher teacher, String gpsTrack) {
        super(student, date);
        this.time = time;
        this.teacher = teacher;
        this.gpsTrack = gpsTrack;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getGpsTrack() {
        return gpsTrack;
    }

    public void setGpsTrack(String gpsTrack) {
        this.gpsTrack = gpsTrack;
    }
}
