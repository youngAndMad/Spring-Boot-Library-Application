package com.example.studentmanagmentsystem.controller;

import com.example.studentmanagmentsystem.StudenrsDAO.StudentDAO;
import com.example.studentmanagmentsystem.entity.Student;
import com.example.studentmanagmentsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("students")
public class StudentController {

    private StudentService studentService;
    private StudentDAO dao;


    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    @GetMapping()
    public String listStudents(Model model){
       model.addAttribute("students" , studentService.getAllStudents());
       return "students";
    }


    @GetMapping("/new")
    public String createStudentForm(Model model){
        model.addAttribute("student" , new  Student());
        return "create_student";
    }
    @PostMapping()
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }



}
