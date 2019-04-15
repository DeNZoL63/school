package com.practice.school.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Date;
import java.util.Optional;

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

    public Teacher(long id, String surname, String firstname, String patronymic, Date birthday, int experience, String licenseNumber) {
        super(surname, firstname, patronymic, birthday);
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Object save(Object o) {
        return o;
    }

    @Override
    public Iterable saveAll(Iterable iterable) {
        return null;
    }

    @Override
    public Optional findById(Object o) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Object o) {
        return false;
    }

    @Override
    public Iterable findAll() {
        return null;
    }

    @Override
    public Iterable findAllById(Iterable iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Object o) {

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void deleteAll(Iterable iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
