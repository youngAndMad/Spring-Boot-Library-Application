package com.example.studentmanagmentsystem.impl;

import com.example.studentmanagmentsystem.entity.Student;
import com.example.studentmanagmentsystem.repository.StudentRepository;
import com.example.studentmanagmentsystem.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository ;

    public StudentServiceImpl(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
       return studentRepository.save(student);
    }
}
