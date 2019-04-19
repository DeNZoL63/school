package com.practice.school.service;

import com.practice.school.entity.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher addTeacher(Teacher teacher);

    Teacher editTeacher(Teacher teacher);

    List<Teacher> getAll();

    void deleteByID(Long id);

    Teacher findById(Long id);
}
