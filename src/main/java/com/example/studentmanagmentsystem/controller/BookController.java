package com.example.studentmanagmentsystem.controller;

import com.example.studentmanagmentsystem.entity.Book;
import com.example.studentmanagmentsystem.service.BookService;
import com.example.studentmanagmentsystem.service.ConnectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@Controller
@RequestMapping("books")
public class BookController {

    private final BookService bookService;
    private final ConnectorService connectorService;

    @Autowired
    public BookController(BookService bookService, ConnectorService connectorService) {
        this.bookService = bookService;
        this.connectorService = connectorService;
    }

    @GetMapping()
    public String listOfBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/new")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        return "create_book";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create_book";
        }
        book.setActive(true);
        bookService.saveBook(book);
        return "redirect:/books";
    }


    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "edit_book";

    }

    @GetMapping("/certainInfo/{id}")
    public String certainBook(Model model, @PathVariable Long id) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("owners",
                connectorService.getAllOrders().
                        stream().
                        filter(order -> order.getBookId().equals(id)).
                        collect(Collectors.toList()));
        return "book_page";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable Long id,
                             @ModelAttribute @Valid Book book,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/books/edit/" + id;
        }

        Book newBook = bookService.getBookById(id);
        newBook.setTitle(book.getTitle());
        newBook.setQuantity(book.getQuantity());
        newBook.setGenre(book.getGenre());
        newBook.setDescription(book.getDescription());
        newBook.setId(book.getId());
        newBook.setYear(book.getYear());
        newBook.setAuthor(book.getAuthor());


        bookService.saveBook(newBook);

        return "redirect:/books";

    }

    @GetMapping("/unActive/{id}")
    public String unActiveBook(@PathVariable Long id) {
        Book bookToUnActive = bookService.getBookById(id);
        bookToUnActive.setActive(false);
        bookService.saveBook(bookToUnActive);
        return "redirect:/books";
    }
}
