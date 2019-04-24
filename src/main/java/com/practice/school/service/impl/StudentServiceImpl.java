package com.practice.school.service.impl;

import com.practice.school.dao.StudentRepository;
import com.practice.school.entity.Student;
import com.practice.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.studentRepository = repository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteByID(Long id) {
        boolean studentExist = studentRepository.existsById(id);

        if (studentExist){
            studentRepository.deleteById(id);
        }
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }
}
