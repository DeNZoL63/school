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
        return teacherRepository.saveAndFlush(teacher);
    }

    @Override
    public Teacher editTeacher(Teacher teacher) {
        return teacherRepository.saveAndFlush(teacher);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public void deleteByID(Long id) {
        boolean teacherExist = teacherRepository.existsById(id);

        if (teacherExist){
            teacherRepository.deleteById(id);
        }
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

}
