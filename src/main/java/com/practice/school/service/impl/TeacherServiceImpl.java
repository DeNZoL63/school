package com.practice.school.service.impl;

import com.practice.school.dao.TeacherRepository;
import com.practice.school.entity.Teacher;
import com.practice.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository repository) {
        this.teacherRepository = repository;
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return null;
    }

    @Override
    public Teacher editTeacher(Teacher teacher) {
        return null;
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public void deleteByID(Long id) {
        boolean studentExist = teacherRepository.existsById(id);

        if (studentExist){
            teacherRepository.deleteById(id);
        }
    }

    @Override
    public Teacher findById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        return teacher;
    }

}
