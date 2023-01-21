package com.example.studentmanagmentsystem.controller;

import com.example.studentmanagmentsystem.entity.Student;
import com.example.studentmanagmentsystem.impl.StudentServiceImpl;
import com.example.studentmanagmentsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudents(Model model){
       model.addAttribute("students" , studentService.getAllStudents());
       return "students";
    }


    @GetMapping("/students/new")
    public String createStudentForm(Model model){

        Student student  =  new Student();
        model.addAttribute("student" , student);
        return "create_student";
    }
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }
}
