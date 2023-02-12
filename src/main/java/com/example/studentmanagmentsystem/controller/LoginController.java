package com.example.studentmanagmentsystem.controller;

import com.example.studentmanagmentsystem.entity.Student;
import com.example.studentmanagmentsystem.service.StudentService;
import com.example.studentmanagmentsystem.login.Login;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class LoginController {

    private final StudentService studentService;

    public LoginController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public String checkForm(Model model){
        model.addAttribute("login" , new Login());
        return "login";
    }


    @PostMapping()
    public String check(@ModelAttribute("login") @Valid  Login login,
                        BindingResult bindingResult,
                        Model model){

        Student student = studentService.findStudent(login.getEmail() , login.getPassword());

        if(bindingResult.hasErrors()){
            return "redirect:/auth";
        }



        if(student!=null){
            model.addAttribute("student" , student);
            return "redirect:/students/certainInfo/" + student.getId();
        }

        return "redirect:/auth";
    }

}
