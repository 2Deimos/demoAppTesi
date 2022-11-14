package com.example.demoapptesi.service;

import com.example.demoapptesi.entity.Student;
import com.example.demoapptesi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
}
