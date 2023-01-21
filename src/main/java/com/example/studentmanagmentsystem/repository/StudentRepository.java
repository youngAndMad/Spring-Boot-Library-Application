package com.example.studentmanagmentsystem.repository;

import com.example.studentmanagmentsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student , Long> {

}
