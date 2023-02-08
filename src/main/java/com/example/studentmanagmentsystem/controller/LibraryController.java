package com.example.studentmanagmentsystem.controller;

import com.example.studentmanagmentsystem.entity.Book;
import com.example.studentmanagmentsystem.entity.Connector;
import com.example.studentmanagmentsystem.entity.Student;
import com.example.studentmanagmentsystem.service.BookService;
import com.example.studentmanagmentsystem.service.ConnectorService;
import com.example.studentmanagmentsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("library")
public class LibraryController {
    private final ConnectorService connectorService;
    private final BookService bookService;
    private final StudentService studentService;

    @Autowired
    public LibraryController(ConnectorService connectorService,
                               BookService bookService,
                               StudentService studentService) {
        this.connectorService = connectorService;
        this.bookService = bookService;
        this.studentService = studentService;
    }

    @GetMapping("/givebook")
    public String giveBook(Model model){
        model.addAttribute("students" , studentService.getAllStudents());
        model.addAttribute("books" , bookService.getAllBooks());
        model.addAttribute("connector" , new Connector());
        return "give_book";
    }
    @GetMapping()
    public String library(Model model){
        model.addAttribute("orders" , connectorService.getAllOrders());
        return "library";
    }

    @PostMapping()
    public String registerBook(@ModelAttribute @Valid Connector connector ,
                               BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "redirect:/library/givebook";
        }

        Student student = studentService.getByName(connector.getStudentName());
        Book book = bookService.getByTitle(connector.getBookTitle());
        connector.setStudentId(student.getId());
        connector.setBookId(book.getId());
        connector.setTakenAt(LocalDateTime.now());
        connectorService.registerBook(connector);
        book.setQuantity(book.getQuantity()-1);
        bookService.saveBook(book);
        return "redirect:/library";

    }

    @GetMapping("/returnbook/{id}")
    public String returnBook(@PathVariable Long id){
        connectorService.deleteById(id);
        return "redirect:/library";
    }

}
