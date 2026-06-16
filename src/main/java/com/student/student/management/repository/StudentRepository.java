package com.student.student.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.student.management.model.Student;

public interface StudentRepository
        extends JpaRepository<Student, Integer> {
List<Student> findByNameContainingIgnoreCase(String name);
}