package com.practice.school.service;

import com.practice.school.entity.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    Student editStudent(Student student);

    List<Student> getAll();

    void deleteByID(Long id);

    Student findById(Long id);

}
