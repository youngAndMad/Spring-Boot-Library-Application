package com.example.studentmanagmentsystem.service;

import com.example.studentmanagmentsystem.entity.Book;
import com.example.studentmanagmentsystem.entity.Student;
import com.example.studentmanagmentsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService {


    private final StudentRepository studentRepository;
    private final ConnectorService connectorService;
    private final BookService bookService;


    @Autowired
    public StudentService(StudentRepository studentRepository,
                          ConnectorService connectorService,
                          BookService bookService) {
        this.studentRepository = studentRepository;
        this.connectorService = connectorService;
        this.bookService = bookService;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student) {
        return saveStudent(student);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);

        List<Long> list = connectorService.getAllOrders()
                .stream().
                filter(o -> o.getStudentId().equals(id))
                .map(e -> e.getBookId()).collect(Collectors.toList());

        for (Long bookId: list) {
           bookService.incrementQuantity(bookId);
        }

    }

    public Student getByName(String name) {
        return studentRepository.findByFirstName(name).orElse(null);
    }


}
