package com.practice.school.views.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Students")
public class Student extends Person {

    @Column(length = 12)
    private String phone;

    @Column
    private String photo;

    @Column
    private boolean haveLicense;

    public Student() {

    }

    public Student(String surname, String firstname, String patronymic, Date birthday, String phone, String photo, boolean haveLicense) {
        super(surname, firstname, patronymic, birthday);
        this.phone = phone;
        this.photo = photo;
        this.haveLicense = haveLicense;
    }

    public Student(long id, String surname, String firstname, String patronymic, Date birthday, String phone, String photo, boolean haveLicense) {
        super(id, surname, firstname, patronymic, birthday);
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

    public boolean isHaveLicense() {
        return haveLicense;
    }
}
