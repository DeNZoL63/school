package com.practice.school.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "STUDENTS")
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(length = 12)
    private String phone;

    @Column
    private String photo;

    @Column
    private boolean haveLicense;

    @Column(length = 50)
    private String surname;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String patronymic;

    @Column
    private LocalDate birthday;

    public Student() {

    }

    public Student(String surname, String name, String patronymic, LocalDate birthday, String phone, String photo, boolean haveLicense) {
//        super(surname, name, patronymic, birthday);
        super();
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;

        this.phone = phone;
        this.photo = photo;
        this.haveLicense = haveLicense;
    }

    public Student(Long id, String surname, String name, String patronymic, LocalDate birthday, String phone, String photo, boolean haveLicense) {
//        super(id, surname, firstname, patronymic, birthday);
//        super(surname, name, patronymic, birthday);
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;

        this.id = id;
        this.phone = phone;
        this.photo = photo;
        this.haveLicense = haveLicense;
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoto() {
        return photo;
    }

    public boolean isHaveLicense() {
        return haveLicense;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setHaveLicense(boolean haveLicense) {
        this.haveLicense = haveLicense;
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
}
