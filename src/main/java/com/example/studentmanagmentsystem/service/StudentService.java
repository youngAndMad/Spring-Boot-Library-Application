package com.example.studentmanagmentsystem.service;

import com.example.studentmanagmentsystem.entity.Connector;
import com.example.studentmanagmentsystem.entity.Student;
import com.example.studentmanagmentsystem.repository.StudentRepository;
import com.example.studentmanagmentsystem.util.Enrichment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StudentService {


    private final StudentRepository studentRepository;
    private final ConnectorService connectorService;
    private final BookService bookService;
    private final Enrichment enrichment;


    @Autowired
    public StudentService(StudentRepository studentRepository,
                          ConnectorService connectorService,
                          BookService bookService,
                          Enrichment enrichment) {
        this.studentRepository = studentRepository;
        this.connectorService = connectorService;
        this.bookService = bookService;
        this.enrichment = enrichment;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll().stream().sorted((student1,student2)-> (int) (student1.getId()-student2.getId())).collect(Collectors.toList());
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void updateStudent(Student student) {
        Student studentToSave = getStudentById(student.getId());
        enrichment.enrichStudent(student,studentToSave);
        saveStudent(studentToSave);
    }
    public List<Connector> getStudentBooks(Long studentId){
      return connectorService.getAllOrders()
                .stream()
                .filter(order -> order.getStudentId()
                        .equals(studentId))
                .collect(Collectors.toList());
    }

    public Student findStudent(String email , String password){
        return studentRepository.findStudentByEmailAndPassword(email,password).orElse(null);
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
