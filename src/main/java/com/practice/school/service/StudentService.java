package com.practice.school.service;

import com.practice.school.entity.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getAll();

}
