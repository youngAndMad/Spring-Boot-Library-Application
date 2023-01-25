package com.example.studentmanagmentsystem.service.impl;

import com.example.studentmanagmentsystem.entity.Student;
import com.example.studentmanagmentsystem.repository.StudentRepository;
import com.example.studentmanagmentsystem.service.Mappers.BookMapper;
import com.example.studentmanagmentsystem.service.Mappers.ConnectorMapper;
import com.example.studentmanagmentsystem.service.serv.StudentService;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository ;
    private JdbcTemplate jdbcTemplate;


    public StudentServiceImpl(StudentRepository studentRepository , JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
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

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return saveStudent(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Integer> studentBooks(Long id){
       return jdbcTemplate.query("select book_id from orders where student_id=" + id ,
               new ConnectorMapper()).stream().map(i -> i.getBookId()).collect(Collectors.toList());
    }


}
