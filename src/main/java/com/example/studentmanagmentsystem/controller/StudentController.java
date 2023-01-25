package com.example.studentmanagmentsystem.controller;

import com.example.studentmanagmentsystem.entity.Student;
import com.example.studentmanagmentsystem.service.serv.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService)  {
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
    public String saveStudent(@ModelAttribute("student") @Valid Student student , BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return   "create_student";
        }

        studentService.saveStudent(student);
        return "redirect:/students";
    }


/*    @PostMapping("/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") @Valid Student student,
                                BindingResult bindingResult,
                                Model model) {

        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }*/


    @GetMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    @GetMapping("/certainInfo/{id}")
    public String certainStudentInfo(@PathVariable Long id , Model model){
        model.addAttribute("student" , studentService.getStudentById(id));
        return "student_page";
    }



}
