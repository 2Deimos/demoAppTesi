package com.example.demoapptesi.repository;

import com.example.demoapptesi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}