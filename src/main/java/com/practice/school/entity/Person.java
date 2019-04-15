package com.practice.school.entity;

import javax.persistence.Column;
import java.util.Date;

public abstract class Person {

    @Column(length = 50)
    private String surname;

    @Column(length = 50)
    private String firstname;

    @Column(length = 50)
    private String patronymic;

    @Column
    private Date birthday;

    Person() {
    }

    Person(String surname, String firstname, String patronymic, Date birthday) {
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
