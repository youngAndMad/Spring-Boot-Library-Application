package com.example.studentmanagmentsystem;

import com.example.studentmanagmentsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagmentSystemApplication implements CommandLineRunner {

    public StudentManagmentSystemApplication(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentManagmentSystemApplication.class, args);
    }

    @Autowired
    private final StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        /*Student s = new Student("beka" , "beak" , "Dsfdghf");
        studentRepository.save(s);
        //Student student = new Student("daneker" , "kaliaskaruly" , "kkraken2005@gmail.com");
       // studentRepository.save(student);*/
    }
}
