package com.practice.school.views.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public abstract class Person {

    @Column(unique = true, nullable = false)
    private long id;

    @Column(length = 50)
    private String surname;

    @Column(length = 50)
    private String firstname;

    @Column(length = 50)
    private String patronymic;

    @Column
    private Date birthday;

    public Person() {
    }

    public Person(long id) {
        this.id = id;
    }

    public Person(long id, String surname, String firstname, String patronymic, Date birthday) {
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.birthday = birthday;
    }

    public Person(String surname, String firstname, String patronymic, Date birthday) {
        this.surname = surname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.birthday = birthday;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }
}
