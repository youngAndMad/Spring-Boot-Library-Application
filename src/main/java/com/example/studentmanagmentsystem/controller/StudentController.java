package com.example.studentmanagmentsystem.controller;

import com.example.studentmanagmentsystem.entity.Student;
import com.example.studentmanagmentsystem.service.ConnectorService;
import com.example.studentmanagmentsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;
    private final ConnectorService connectorService;


    @Autowired
    public StudentController(StudentService studentService, ConnectorService connectorService) {
        this.studentService = studentService;
        this.connectorService = connectorService;
    }

    @GetMapping()
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }


    @GetMapping("/new")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "create_student";
    }

    @PostMapping()
    public String saveStudent(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "create_student";
        }

        studentService.saveStudent(student);
        return "redirect:/students";
    }


    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {

        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") @Valid Student student,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/students/edit/" + id;
        }

        studentService.updateStudent(student);
        return "redirect:/students";

    }


    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    @GetMapping("/certainInfo/{id}")
    public String certainStudentInfo(@PathVariable Long id, Model model) {

        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("books",
                connectorService.getAllOrders().
                        stream().
                        filter(order -> order.getStudentId().equals(id)).
                        collect(Collectors.toList()));

        return "student_page";
    }

}
