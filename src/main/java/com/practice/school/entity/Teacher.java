package com.practice.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Teacher")
public class Teacher extends Person {

    @Column
    private String experience;

    @Column(length = 6, unique = true)
    private String licenseNumber;

    public Teacher() {
    }

    public Teacher(long id) {
        super(id);
    }

    public Teacher(long id, String surname, String name, String patronymic, LocalDate birthday, String experience, String licenseNumber) {
        super(id, surname, name, patronymic, birthday);
        this.experience = experience;
        this.licenseNumber = licenseNumber;
    }

    public Teacher(String surname, String name, String patronymic, LocalDate birthday, String experience, String licenseNumber) {
        super(surname, name, patronymic, birthday);
        this.experience = experience;
        this.licenseNumber = licenseNumber;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
