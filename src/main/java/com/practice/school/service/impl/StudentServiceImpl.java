package com.practice.school.service.impl;

import com.practice.school.dao.StudentRepository;
import com.practice.school.entity.Student;
import com.practice.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    //#TODO получил репозиторий
//    @Inject
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.studentRepository = repository;
    }

    @Override
    public Student addStudent(Student student) {
        Student savedStudent = studentRepository.save(student);

        return savedStudent;
    }

    @Override
    public List<Student> getAll() {
        return (List<Student>) studentRepository.findAll();
    }

}
