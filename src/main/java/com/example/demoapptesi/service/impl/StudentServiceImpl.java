package com.example.demoapptesi.service.impl;

import com.example.demoapptesi.entity.Student;
import com.example.demoapptesi.repository.StudentRepository;
import com.example.demoapptesi.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
