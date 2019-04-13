package com.practice.school.views.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "Teachers")
public class Teacher extends Person {

    @Column
    private int experience;

    @Column(unique = true)
    private String licenseNumber;

    public Teacher() {
    }

    public Teacher(long id) {
        super(id);
    }

    public Teacher(long id, String surname, String firstname, String patronymic, Date birthday, int experience, String licenseNumber) {
        super(id, surname, firstname, patronymic, birthday);
        this.experience = experience;
        this.licenseNumber = licenseNumber;
    }

    public Teacher(String surname, String firstname, String patronymic, Date birthday, int experience, String licenseNumber) {
        super(surname, firstname, patronymic, birthday);
        this.experience = experience;
        this.licenseNumber = licenseNumber;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
