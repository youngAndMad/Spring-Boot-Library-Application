package com.example.studentmanagmentsystem.controller;

import com.example.studentmanagmentsystem.entity.Book;
import com.example.studentmanagmentsystem.repository.BookRepository;
import com.example.studentmanagmentsystem.service.serv.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        super();
        this.bookService = bookService;
    }

    @GetMapping()
    public String listOfBooks(Model model){
        model.addAttribute("books" , bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/new")
    public String createBook(Model model){
        model.addAttribute("book",new Book());
        return "create_book";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute("book") Book book){
        bookService.saveBook(book);
       return  "redirect:/books";
    }
}
