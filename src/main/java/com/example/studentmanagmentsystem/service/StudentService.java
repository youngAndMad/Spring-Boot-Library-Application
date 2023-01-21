package com.example.studentmanagmentsystem.service;

import com.example.studentmanagmentsystem.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student>  getAllStudents();
    Student saveStudent(Student student);
}
