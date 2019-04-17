package com.practice.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "STUDENTS")
public class Student extends Person {

    @Column(length = 12)
    private String phone;

    @Column
    private String photo;

    @Column
    private boolean haveLicense;

    public Student() {
    }

    public Student(Long id) {
        super(id);
    }

    public Student(String surname, String name, String patronymic, LocalDate birthday, String phone, String photo, boolean haveLicense) {
        super(surname, name, patronymic, birthday);
        this.phone = phone;
        this.photo = photo;
        this.haveLicense = haveLicense;
    }

    public Student(Long id, String surname, String name, String patronymic, LocalDate birthday, String phone, String photo, boolean haveLicense) {
        super(id, surname, name, patronymic, birthday);
        this.phone = phone;
        this.photo = photo;
        this.haveLicense = haveLicense;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoto() {
        return photo;
    }

    public boolean getHaveLicense() {
        return haveLicense;
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
}
