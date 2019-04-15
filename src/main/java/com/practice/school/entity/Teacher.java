package com.practice.school.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.time.LocalDate;

@Table(name = "Teachers")
public class Teacher extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column
    private int experience;

    @Column(unique = true)
    private String licenseNumber;

    public Teacher() {
    }

    public Teacher(long id) {
//        super(id);
        this.id = id;
    }

    public Teacher(long id, String surname, String name, String patronymic, LocalDate birthday, int experience, String licenseNumber) {
//        super(surname, name, patronymic, birthday);
        this.id = id;
        this.experience = experience;
        this.licenseNumber = licenseNumber;
    }

    public Teacher(String surname, String name, String patronymic, LocalDate birthday, int experience, String licenseNumber) {
//        super(surname, name, patronymic, birthday);
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
