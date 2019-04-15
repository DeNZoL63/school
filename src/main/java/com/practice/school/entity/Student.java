package com.practice.school.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "Students")
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

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
//        super(id, surname, firstname, patronymic, birthday);
        super(surname, firstname, patronymic, birthday);
        this.id = id;
        this.phone = phone;
        this.photo = photo;
        this.haveLicense = haveLicense;
    }

    public long getId() {
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


    @Override
    public Object save(Object o) {
        return null;
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
