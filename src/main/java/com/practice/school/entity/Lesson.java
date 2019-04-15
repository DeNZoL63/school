package com.practice.school.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Table(name = "Lessons")
public class Lesson extends StudentAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private Time time;

    @Column
    private Teacher teacher;

    @Column
    private String gpsTrack;

    public Lesson() {
    }

    public Lesson(long id, Student student, Date date, Time time, Teacher teacher, String gpsTrack) {
        super(student, date);
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
