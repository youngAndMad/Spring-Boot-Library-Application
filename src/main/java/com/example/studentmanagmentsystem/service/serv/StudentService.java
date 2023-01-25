package com.example.studentmanagmentsystem.service.serv;

import com.example.studentmanagmentsystem.entity.Student;
import java.util.List;

public interface StudentService{
    List<Student>  getAllStudents();
    Student updateStudent(Student student);
    Student getStudentById(Long id);
    void deleteStudentById(Long id);
    Student saveStudent(Student student);
    List<Integer> studentBooks(Long studentId);
}
