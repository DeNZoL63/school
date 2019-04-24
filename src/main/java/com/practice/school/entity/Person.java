package com.practice.school.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Person extends BaseEntity{

    @Column(length = 50)
    private String surname;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String patronymic;

    @Column
    private LocalDate birthday;

    Person() {
    }

    Person(Long id) {
        super(id);
    }

    Person(String surname, String name, String patronymic, LocalDate birthday) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
    }

    Person(Long id, String surname, String name, String patronymic, LocalDate birthday) {
        super(id);
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
