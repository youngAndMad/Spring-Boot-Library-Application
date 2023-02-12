package com.example.studentmanagmentsystem.repository;

import com.example.studentmanagmentsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student , Long> {
    Optional<Student> findByFirstName(String name);
    Optional<Student> findStudentByEmailAndPassword(String email , String password);
}
