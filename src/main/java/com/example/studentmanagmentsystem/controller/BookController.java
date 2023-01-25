package com.example.studentmanagmentsystem.controller;

import com.example.studentmanagmentsystem.entity.Book;
import com.example.studentmanagmentsystem.service.serv.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


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
    public String saveBook(@ModelAttribute("book") @Valid Book book , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "create_book";
        }
        bookService.saveBook(book);
       return  "redirect:/books";
    }

    @GetMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}
